package com.bignerdranch.draganddraw

class BoxesRepository private constructor() {
    private val _boxes = mutableListOf<Box>()

    // add a new box
    fun addBox(box:Box) { _boxes.add(box) }

    // return an immutable list of boxes upon request
    fun getBoxes(): List<Box> = _boxes.toList()

    companion object {
        private var INSTANCE: BoxesRepository? = null

        // initialize the singleton
        fun initialize() {
            if (INSTANCE == null)
                INSTANCE = BoxesRepository()
        }

        // define a get() function for getting access to class methods
        fun get() = INSTANCE ?: throw java.lang.Exception("Instance not initialized.")

    }
}