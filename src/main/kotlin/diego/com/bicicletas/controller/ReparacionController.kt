package diego.com.bicicletas.controller

import diego.com.bicicletas.model.cliente.Cliente
import diego.com.bicicletas.model.nuevareparacion.NuevaReparacion
import diego.com.bicicletas.model.reparacion.Reparacion
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/reparaciones")
class ReparacionController {
    private val reparaciones = mutableListOf(
        Reparacion(
            id = 1,
            bicicleta = "Bicicleta de montana",
            cliente = Cliente("Luis Perez", "0991234567"),
            repuestos = listOf("cadena", "freno"),
            costo = 25.0,
            lista = true
        ),
        Reparacion(
            id = 2,
            bicicleta = "Bicicleta urbana",
            cliente = Cliente("Maria Lopez", "0987654321"),
            repuestos = listOf("luz"),
            costo = 12.5,
            lista = false
        )
    )

    @GetMapping
    fun listar(): List<Reparacion> = reparaciones

    @GetMapping("/{id}")
    fun obtener(@PathVariable id: Int): ResponseEntity<Reparacion> {
        val reparacion = reparaciones.find { it.id == id }
            ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(reparacion)
    }

    @PostMapping
    fun crear(@RequestBody nueva: NuevaReparacion): ResponseEntity<Reparacion> {
        val reparacion = Reparacion(
            id = (reparaciones.maxOfOrNull { it.id } ?: 0) + 1,
            bicicleta = nueva.bicicleta,
            cliente = nueva.cliente,
            repuestos = nueva.repuestos,
            costo = nueva.costo,
            lista = false
        )
        reparaciones.add(reparacion)
        return ResponseEntity.created(URI.create("/reparaciones/${reparacion.id}"))
            .body(reparacion)
    }
}
