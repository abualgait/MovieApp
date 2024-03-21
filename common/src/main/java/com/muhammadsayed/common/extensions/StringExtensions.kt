package com.muhammadsayed.common.extensions

fun String.getYearFromDate() = this.split("-").firstOrNull()