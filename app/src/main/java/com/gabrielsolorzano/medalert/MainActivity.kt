package com.gabrielsolorzano.medalert

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gabrielsolorzano.medalert.ui.theme.MedAlertTheme
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MedAlertTheme {
                MedAlertApp(applicationContext)
            }
        }
    }
}

// =========================================================
// MODELOS
// =========================================================

data class Medicamento(
    val nombre: String,
    val dosis: String,
    val horario: String
)

data class FichaEmergencia(
    val alergias: String = "",
    val antecedentes: String = "",
    val contacto: String = ""
)

// =========================================================
// ALMACENAMIENTO LOCAL
// =========================================================

object MedAlertStorage {

    private const val PREFS = "medalert_preferences"
    private const val KEY_MEDICAMENTOS = "medicamentos"
    private const val KEY_ALERGIAS = "alergias"
    private const val KEY_ANTECEDENTES = "antecedentes"
    private const val KEY_CONTACTO = "contacto"

    fun guardarMedicamentos(
        context: Context,
        medicamentos: List<Medicamento>
    ) {
        val jsonArray = JSONArray()

        medicamentos.forEach { medicamento ->
            val objeto = JSONObject()
            objeto.put("nombre", medicamento.nombre)
            objeto.put("dosis", medicamento.dosis)
            objeto.put("horario", medicamento.horario)
            jsonArray.put(objeto)
        }

        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_MEDICAMENTOS, jsonArray.toString())
            .apply()
    }

    fun cargarMedicamentos(context: Context): List<Medicamento> {

        val texto = context
            .getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            .getString(KEY_MEDICAMENTOS, null)
            ?: return emptyList()

        return try {

            val jsonArray = JSONArray(texto)

            List(jsonArray.length()) { indice ->

                val objeto = jsonArray.getJSONObject(indice)

                Medicamento(
                    nombre = objeto.optString("nombre"),
                    dosis = objeto.optString("dosis"),
                    horario = objeto.optString("horario")
                )
            }

        } catch (_: Exception) {
            emptyList()
        }
    }

    fun guardarFicha(
        context: Context,
        ficha: FichaEmergencia
    ) {
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_ALERGIAS, ficha.alergias)
            .putString(KEY_ANTECEDENTES, ficha.antecedentes)
            .putString(KEY_CONTACTO, ficha.contacto)
            .apply()
    }

    fun cargarFicha(context: Context): FichaEmergencia {

        val prefs =
            context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)

        return FichaEmergencia(
            alergias = prefs.getString(KEY_ALERGIAS, "") ?: "",
            antecedentes =
                prefs.getString(KEY_ANTECEDENTES, "") ?: "",
            contacto = prefs.getString(KEY_CONTACTO, "") ?: ""
        )
    }
}

// =========================================================
// APLICACIÓN
// =========================================================

@Composable
fun MedAlertApp(context: Context) {

    var pantalla by remember { mutableStateOf("inicio") }

    val medicamentos = remember {
        mutableStateListOf<Medicamento>().apply {
            addAll(MedAlertStorage.cargarMedicamentos(context))
        }
    }

    var ficha by remember {
        mutableStateOf(MedAlertStorage.cargarFicha(context))
    }

    var medicamentoEditar by remember {
        mutableStateOf<Int?>(null)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        when (pantalla) {

            "inicio" -> PantallaInicio(
                modifier = Modifier.padding(innerPadding),
                cantidadMedicamentos = medicamentos.size,
                onMedicamentos = {
                    pantalla = "medicamentos"
                },
                onAgregar = {
                    medicamentoEditar = null
                    pantalla = "agregar"
                },
                onEmergencia = {
                    pantalla = "emergencia"
                }
            )

            "medicamentos" -> PantallaMedicamentos(
                modifier = Modifier.padding(innerPadding),
                medicamentos = medicamentos,
                onVolver = {
                    pantalla = "inicio"
                },
                onAgregar = {
                    medicamentoEditar = null
                    pantalla = "agregar"
                },
                onEditar = { indice ->
                    medicamentoEditar = indice
                    pantalla = "agregar"
                },
                onEliminar = { indice ->
                    medicamentos.removeAt(indice)

                    MedAlertStorage.guardarMedicamentos(
                        context,
                        medicamentos
                    )
                }
            )

            "agregar" -> {

                val actual =
                    medicamentoEditar?.let { medicamentos[it] }

                PantallaFormularioMedicamento(
                    modifier = Modifier.padding(innerPadding),
                    medicamento = actual,
                    onGuardar = { medicamento ->

                        val indice = medicamentoEditar

                        if (indice == null) {
                            medicamentos.add(medicamento)
                        } else {
                            medicamentos[indice] = medicamento
                        }

                        MedAlertStorage.guardarMedicamentos(
                            context,
                            medicamentos
                        )

                        medicamentoEditar = null
                        pantalla = "medicamentos"
                    },
                    onCancelar = {
                        medicamentoEditar = null
                        pantalla = "medicamentos"
                    }
                )
            }

            "emergencia" -> PantallaEmergencia(
                modifier = Modifier.padding(innerPadding),
                fichaInicial = ficha,
                onGuardar = { nuevaFicha ->

                    ficha = nuevaFicha

                    MedAlertStorage.guardarFicha(
                        context,
                        nuevaFicha
                    )

                    pantalla = "inicio"
                },
                onCancelar = {
                    pantalla = "inicio"
                }
            )
        }
    }
}

// =========================================================
// INICIO
// =========================================================

@Composable
fun PantallaInicio(
    modifier: Modifier = Modifier,
    cantidadMedicamentos: Int,
    onMedicamentos: () -> Unit,
    onAgregar: () -> Unit,
    onEmergencia: () -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "MedAlert",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Gestor personal de medicamentos",
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = if (cantidadMedicamentos == 1)
                "1 medicamento registrado"
            else
                "$cantidadMedicamentos medicamentos registrados",
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(36.dp))

        Button(
            onClick = onMedicamentos,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Mis medicamentos")
        }

        Spacer(modifier = Modifier.height(14.dp))

        Button(
            onClick = onAgregar,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar medicamento")
        }

        Spacer(modifier = Modifier.height(14.dp))

        OutlinedButton(
            onClick = onEmergencia,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ficha de emergencia")
        }

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Los datos se almacenan localmente en este dispositivo.",
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "COM 437 • Saint Leo University",
            fontSize = 12.sp
        )
    }
}

// =========================================================
// LISTA DE MEDICAMENTOS
// =========================================================

@Composable
fun PantallaMedicamentos(
    modifier: Modifier = Modifier,
    medicamentos: List<Medicamento>,
    onVolver: () -> Unit,
    onAgregar: () -> Unit,
    onEditar: (Int) -> Unit,
    onEliminar: (Int) -> Unit
) {

    var eliminarIndice by remember {
        mutableStateOf<Int?>(null)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = "Mis medicamentos",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (medicamentos.isEmpty()) {

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Aún no hay medicamentos registrados.",
                    modifier = Modifier.padding(20.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

        } else {

            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {

                itemsIndexed(medicamentos) { indice, medicamento ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 4.dp
                        )
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            Text(
                                text = medicamento.nombre,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Text("Dosis: ${medicamento.dosis}")

                            Text("Horario: ${medicamento.horario}")

                            Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement =
                                    Arrangement.SpaceBetween
                            ) {

                                TextButton(
                                    onClick = {
                                        onEditar(indice)
                                    }
                                ) {
                                    Text("Editar")
                                }

                                TextButton(
                                    onClick = {
                                        eliminarIndice = indice
                                    }
                                ) {
                                    Text("Eliminar")
                                }
                            }
                        }
                    }
                }
            }
        }

        Button(
            onClick = onAgregar,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar medicamento")
        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedButton(
            onClick = onVolver,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver al inicio")
        }
    }

    eliminarIndice?.let { indice ->

        if (indice in medicamentos.indices) {

            AlertDialog(
                onDismissRequest = {
                    eliminarIndice = null
                },

                title = {
                    Text("Eliminar medicamento")
                },

                text = {
                    Text(
                        "¿Desea eliminar ${medicamentos[indice].nombre}?"
                    )
                },

                confirmButton = {

                    TextButton(
                        onClick = {
                            onEliminar(indice)
                            eliminarIndice = null
                        }
                    ) {
                        Text("Eliminar")
                    }
                },

                dismissButton = {

                    TextButton(
                        onClick = {
                            eliminarIndice = null
                        }
                    ) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }
}

// =========================================================
// FORMULARIO MEDICAMENTO
// =========================================================

@Composable
fun PantallaFormularioMedicamento(
    modifier: Modifier = Modifier,
    medicamento: Medicamento?,
    onGuardar: (Medicamento) -> Unit,
    onCancelar: () -> Unit
) {

    var nombre by remember(medicamento) {
        mutableStateOf(medicamento?.nombre ?: "")
    }

    var dosis by remember(medicamento) {
        mutableStateOf(medicamento?.dosis ?: "")
    }

    var horario by remember(medicamento) {
        mutableStateOf(medicamento?.horario ?: "")
    }

    var mostrarError by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text(
            text = if (medicamento == null)
                "Agregar medicamento"
            else
                "Editar medicamento",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = {
                nombre = it
                mostrarError = false
            },
            label = {
                Text("Nombre del medicamento")
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = dosis,
            onValueChange = {
                dosis = it
                mostrarError = false
            },
            label = {
                Text("Dosis")
            },
            placeholder = {
                Text("Ej.: 500 mg")
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = horario,
            onValueChange = {
                horario = it
                mostrarError = false
            },
            label = {
                Text("Horario")
            },
            placeholder = {
                Text("Ej.: 08:00 y 20:00")
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        if (mostrarError) {

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Complete nombre, dosis y horario.",
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        Button(
            onClick = {

                if (
                    nombre.isBlank() ||
                    dosis.isBlank() ||
                    horario.isBlank()
                ) {

                    mostrarError = true

                } else {

                    onGuardar(
                        Medicamento(
                            nombre = nombre.trim(),
                            dosis = dosis.trim(),
                            horario = horario.trim()
                        )
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                if (medicamento == null)
                    "Guardar medicamento"
                else
                    "Guardar cambios"
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedButton(
            onClick = onCancelar,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cancelar")
        }
    }
}

// =========================================================
// FICHA DE EMERGENCIA
// =========================================================

@Composable
fun PantallaEmergencia(
    modifier: Modifier = Modifier,
    fichaInicial: FichaEmergencia,
    onGuardar: (FichaEmergencia) -> Unit,
    onCancelar: () -> Unit
) {

    var alergias by remember(fichaInicial) {
        mutableStateOf(fichaInicial.alergias)
    }

    var antecedentes by remember(fichaInicial) {
        mutableStateOf(fichaInicial.antecedentes)
    }

    var contacto by remember(fichaInicial) {
        mutableStateOf(fichaInicial.contacto)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text(
            text = "Ficha de emergencia",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Información básica seleccionada por el usuario."
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = alergias,
            onValueChange = {
                alergias = it
            },
            label = {
                Text("Alergias")
            },
            placeholder = {
                Text("Ej.: Penicilina")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = antecedentes,
            onValueChange = {
                antecedentes = it
            },
            label = {
                Text("Antecedentes relevantes")
            },
            placeholder = {
                Text("Ej.: Hipertensión arterial")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = contacto,
            onValueChange = {
                contacto = it
            },
            label = {
                Text("Contacto de emergencia")
            },
            placeholder = {
                Text("Nombre y teléfono")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(28.dp))

        Button(
            onClick = {

                onGuardar(
                    FichaEmergencia(
                        alergias = alergias.trim(),
                        antecedentes = antecedentes.trim(),
                        contacto = contacto.trim()
                    )
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar ficha")
        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedButton(
            onClick = onCancelar,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cancelar")
        }
    }
}