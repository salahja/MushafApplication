package Utility

class AudioPlayed {
    var surah = 0
    var ayah = 0
    var trackposition = 0

    constructor()
    constructor(surah: Int, ayah: Int, trackposition: Int) {
        this.surah = surah
        this.ayah = ayah
        this.trackposition = trackposition
    }
}