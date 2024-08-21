package com.example.mushafconsolidated.Activityimport


class AyahCoordinate {
    var start: Int? = null
    var end: Int? = null
    var passage: Int? = null

    constructor() {}
    constructor(start: Int?, end: Int?, passage: Int?) {
        this.start = start
        this.end = end
        this.passage = passage
    }

    constructor(start: Int?, end: Int?) {
        this.start = start
        this.end = end
    }
}