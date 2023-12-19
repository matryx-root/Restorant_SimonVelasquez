package com.example.profesor

class CuentaMesa(val numeroMesa: Int) {
    private val itemsMesa = mutableMapOf<ItemMenu, ItemMesa>()

    fun actualizarCantidad(itemMenu: ItemMenu, cantidad: Int) {
        if (cantidad > 0) {
            itemsMesa[itemMenu]?.cantidad = cantidad
        } else {
            itemsMesa.remove(itemMenu)
        }
    }

    fun calcularSubtotalItem(itemMenu: ItemMenu): Int {
        val itemMesa = itemsMesa[itemMenu]
        return itemMesa?.cantidad?.times(itemMenu.precio) ?: 0
    }

    fun calcularSubtotalTotal(): Int {
        return itemsMesa.values.sumBy { it.cantidad * it.itemMenu.precio }
    }

    fun calcularPropina(): Int {
        // Puedes personalizar la lógica de cálculo de la propina aquí
        return (0.1 * calcularSubtotalTotal()).toInt()
    }
}