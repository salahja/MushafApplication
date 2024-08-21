class AudioPlayed {
    var surah: Int = 0
    var ayah: Int = 0
    var trackposition: Int = 0

    constructor()
    constructor(surah: Int, ayah: Int, trackposition: Int) {
        this.surah = surah
        this.ayah = ayah
        this.trackposition = trackposition
    }
}