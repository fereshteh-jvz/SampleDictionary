package com.shetabit.sampledictionary.data.remote

data class WordDetailResponse(
    val word: String,
    val meanings: List<Meanings>
) {


    class Meanings {
        var partOfSpeech: String? = null
        var definitions: List<Definitions>? = null
    }

    class Definitions {
        var definition: String? = null
        var example: String? = null
    }

}
