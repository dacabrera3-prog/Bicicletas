package diego.com.bicicletas.model.reparacion

import diego.com.bicicletas.model.cliente.Cliente

data class Reparacion(
    val id: Int,
    val bicicleta: String,
    val cliente: Cliente,
    val repuestos: List<String>,
    val costo: Double,
    val lista: Boolean
)
