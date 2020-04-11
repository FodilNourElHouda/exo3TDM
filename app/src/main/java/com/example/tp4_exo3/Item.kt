
package com.example.tp4_exo3

class Item(tacheAn : Int, tacheMoi: Int, tacheJr: Int,nomTache : String) : Comparable<Item>{
    var nom = nomTache
    var annee = tacheAn
    var mois =  tacheMoi
    var jour =tacheJr



    override fun compareTo(other: Item): Int {
        return ("$nom-$annee-$mois-$jour").compareTo("${other.nom}-${other.annee}-${other.mois}-${other.jour}")
    }

    override fun hashCode(): Int {
        return ("$nom-$annee-$mois-$jour").hashCode()
    }
    fun dateToString() : String{
        return "$jour-${mois+1}-$annee"
    }

}