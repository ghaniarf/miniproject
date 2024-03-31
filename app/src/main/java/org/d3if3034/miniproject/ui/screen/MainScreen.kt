package org.d3if3034.miniproject.ui.screen

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.d3if3034.miniproject.ui.theme.MiniprojectTheme
import org.d3if3034.miniproject.R
import org.d3if3034.miniproject.navigation.Screen

@Composable
fun ScreenContent(modifier: Modifier) {
    var koefisien by rememberSaveable { mutableStateOf("") }
    var koefisienError by rememberSaveable { mutableStateOf(false) }
    var gayaNormal by rememberSaveable { mutableStateOf("") }
    var gayaNormalError by rememberSaveable { mutableStateOf(false) }

    var massaBenda by rememberSaveable { mutableStateOf("") }
    var massaBendaError by rememberSaveable { mutableStateOf(false) }
    var gravitasi by rememberSaveable { mutableStateOf("") }
    var gravitasiError by rememberSaveable { mutableStateOf(false) }

    var massaJenis by rememberSaveable { mutableStateOf("") }
    var massaJenisError by rememberSaveable { mutableStateOf(false) }
    var beratBenda by rememberSaveable { mutableStateOf("") }
    var beratBendaError by rememberSaveable { mutableStateOf(false) }

    val radioOptions = listOf(
        stringResource(id = R.string.gaya_gesek),
        stringResource(id = R.string.gaya_berat),
        stringResource(id = R.string.berat_sejenis)
    )
    var rumus by rememberSaveable { mutableStateOf(radioOptions[0]) }

    var hitungGesek by rememberSaveable { mutableFloatStateOf(0f) }
    var hitungBerat by rememberSaveable { mutableFloatStateOf(0f) }
    var hitungSejenis by rememberSaveable { mutableFloatStateOf(0f) }

    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.intro),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            modifier = Modifier
                .padding(top = 6.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
        ) {
            radioOptions.forEach { text ->
                Option(
                    label = text,
                    isSelected = rumus == text,
                    modifier = Modifier
                        .selectable(
                            selected = rumus == text,
                            onClick = { rumus = text },
                            role = Role.RadioButton
                        )
                        .weight(1f)
                        .padding(16.dp)
                )
            }
        }
        when (rumus) {
            stringResource(id = R.string.gaya_gesek) -> {
                OutlinedTextField(
                    value = koefisien,
                    onValueChange = { koefisien = it },
                    label = { Text(text = stringResource(R.string.koefisien)) },
                    trailingIcon = { IconPicker(koefisienError, "") },
                    supportingText = { ErrorHint(koefisienError) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = gayaNormal,
                    onValueChange = { gayaNormal = it },
                    label = { Text(text = stringResource(R.string.gaya_normal)) },
                    trailingIcon = { IconPicker(gayaNormalError, "N") },
                    supportingText = { ErrorHint(gayaNormalError) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = {
                            koefisienError = (koefisien == "" || koefisien == "0")
                            gayaNormalError = (gayaNormal == "" || gayaNormal == "0")
                            if (koefisienError || gayaNormalError) return@Button

                            hitungGesek = hitungGesek(koefisien.toFloat(), gayaNormal.toFloat())
                        },
                        modifier = Modifier.padding(top = 8.dp),
                        contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
                    ) {
                        Text(text = stringResource(R.string.hitung))
                    }
                    Button(
                        onClick = {
                            koefisien = ""
                            gayaNormal = ""
                            hitungGesek = 0f
                        },
                        modifier = Modifier.padding(top = 8.dp),
                        contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
                    ) {
                        Text("Reset")
                    }
                }
                if (hitungGesek != 0f) {
                    Divider(
                        modifier = Modifier.padding(vertical = 8.dp),
                        thickness = 1.dp
                    )
                    Text(
                        text = stringResource(R.string.gaya_gesek_x, hitungGesek),
                        style = MaterialTheme.typography.titleLarge
                    )
                    Button(
                        onClick = {
                            shareData(
                                context = context,
                                message = context.getString(R.string.template1, koefisien, gayaNormal, context.getString(R.string.gaya_gesek_x))
                            )
                        },
                        modifier = Modifier.padding(top = 8.dp),
                        contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
                    ) {
                        Text(text = stringResource(R.string.bagikan))
                    }
                }
            }
            stringResource(id = R.string.gaya_berat) -> {
                OutlinedTextField(
                    value = massaBenda,
                    onValueChange = { massaBenda = it },
                    label = { Text(text = stringResource(R.string.massa_benda)) },
                    trailingIcon = { IconPicker(massaBendaError, "kg") },
                    supportingText = { ErrorHint(massaBendaError) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = gravitasi,
                    onValueChange = { gravitasi = it },
                    label = { Text(text = stringResource(R.string.gravitasi)) },
                    trailingIcon = { IconPicker(gravitasiError, "m/s2") },
                    supportingText = { ErrorHint(gravitasiError) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = {
                            massaBendaError = (massaBenda == "" || massaBenda == "0")
                            gravitasiError = (gravitasi == "" || gravitasi == "0")
                            if (massaBendaError || gravitasiError) return@Button

                            hitungBerat = hitungBerat(massaBenda.toFloat(), gravitasi.toFloat())
                        },
                        modifier = Modifier.padding(top = 8.dp),
                        contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
                    ) {
                        Text(text = stringResource(R.string.hitung))
                    }
                    Button(
                        onClick = {
                            massaBenda = ""
                            gravitasi = ""
                            hitungBerat = 0f
                        },
                        modifier = Modifier.padding(top = 8.dp),
                        contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
                    ) {
                        Text("Reset")
                    }
                }
                if (hitungBerat != 0f) {
                    Divider(
                        modifier = Modifier.padding(vertical = 8.dp),
                        thickness = 1.dp
                    )
                    Text(
                        text = stringResource(R.string.gaya_berat_x, hitungBerat),
                        style = MaterialTheme.typography.titleLarge
                    )
                    Button(
                        onClick = {
                            shareData(
                                context = context,
                                message = context.getString(R.string.template2, massaBenda, gravitasi, context.getString(R.string.gaya_berat_x))
                            )
                        },
                        modifier = Modifier.padding(top = 8.dp),
                        contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
                    ) {
                        Text(text = stringResource(R.string.bagikan))
                    }
                }
            }
            stringResource(id = R.string.berat_sejenis) -> {
                OutlinedTextField(
                    value = massaJenis,
                    onValueChange = { massaJenis = it },
                    label = { Text(text = stringResource(R.string.massa_jenis)) },
                    trailingIcon = { IconPicker(massaJenisError, "kg/m3") },
                    supportingText = { ErrorHint(massaJenisError) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = beratBenda,
                    onValueChange = { beratBenda = it },
                    label = { Text(text = stringResource(R.string.berat_benda)) },
                    trailingIcon = { IconPicker(beratBendaError, "N") },
                    supportingText = { ErrorHint(beratBendaError) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = {
                            massaJenisError = (massaJenis == "" || massaJenis == "0")
                            beratBendaError = (beratBenda == "" || beratBenda== "0")
                            if (massaJenisError || beratBendaError) return@Button

                            hitungSejenis = hitungSejenis(massaJenis.toFloat(), beratBenda.toFloat())
                        },
                        modifier = Modifier.padding(top = 8.dp),
                        contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
                    ) {
                        Text(text = stringResource(R.string.hitung))
                    }
                    Button(
                        onClick = {
                            massaJenis = ""
                            beratBenda = ""
                            hitungSejenis = 0f
                        },
                        modifier = Modifier.padding(top = 8.dp),
                        contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
                    ) {
                        Text("Reset")
                    }
                }
                if (hitungSejenis != 0f) {
                    Divider(
                        modifier = Modifier.padding(vertical = 8.dp),
                        thickness = 1.dp
                    )
                    Text(
                        text = stringResource(R.string.berat_sejenis_x,hitungSejenis),
                        style = MaterialTheme.typography.titleLarge
                    )
                    Button(
                        onClick = {
                            shareData(
                                context = context,
                                message = context.getString(R.string.template3, massaJenis, beratBenda, context.getString(R.string.berat_sejenis_x))
                            )
                        },
                        modifier = Modifier.padding(top = 8.dp),
                        contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
                    ) {
                        Text(text = stringResource(R.string.bagikan))
                    }
                }
            }
        }
    }
}

@Composable
fun Option(label: String, isSelected: Boolean, modifier: Modifier) {
    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ){
        RadioButton(selected = isSelected, onClick = null )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

private fun hitungGesek(koefisien: Float, gayaNormal: Float): Float {
    return koefisien * gayaNormal
}

private fun hitungBerat(massaBenda: Float, gravitasi: Float): Float {
    return massaBenda * gravitasi
}

private fun hitungSejenis(massaJenis: Float, beratBenda: Float): Float {
    return massaJenis * beratBenda
}

private fun shareData(context: Context, message: String) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    if (shareIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(shareIntent)
    }
}

@Composable
fun IconPicker(isError: Boolean, unit: String) {
    if (isError) {
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    } else {
        Text(text = unit)
    }
}

@Composable
fun ErrorHint(isError: Boolean) {
    if (isError) {
        Text(text = stringResource(R.string.input_invalid))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    Scaffold (
        topBar = {
            TopAppBar(title = {
                Text(text = stringResource(id = R.string.app_name))
            },

                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.About.route)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = stringResource(R.string.tentang_aplikasi),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    ) { padding ->
        ScreenContent(Modifier.padding(padding))
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ScreenPreview() {
    MiniprojectTheme {
        MainScreen(rememberNavController())
    }
}