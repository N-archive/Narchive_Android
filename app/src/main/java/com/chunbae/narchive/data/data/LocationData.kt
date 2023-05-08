package com.chunbae.narchive.data.data

import java.io.Serializable

data class LocationData (
    val road_address_name : String,
    val place_name : String,
    val x : String,
    val y : String
) : Serializable