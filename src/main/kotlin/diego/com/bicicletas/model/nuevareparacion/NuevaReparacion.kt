package diego.com.bicicletas.model.nuevareparacion

import diego.com.bicicletas.model.cliente.Cliente

data class NuevaReparacion(
    val bicicleta: String,
    val cliente: Cliente,
    val repuestos: List<String> = emptyList(),
    val costo: Double
)
