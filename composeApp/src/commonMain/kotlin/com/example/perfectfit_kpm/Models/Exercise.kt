package com.example.perfectfit_kpm

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Exercise(
    @SerialName("name") val name: String,
    @SerialName("image") val imageRes: String,
    @SerialName("description_patient") var shortDescription: String,
    @SerialName("description_clinical") var longDescription: String,
    @SerialName("body_part") var bodyPart: String = "N/A",
    @SerialName("equipment") var equipmentRequired: String = "None",
    @SerialName("sets_reps") var sets: String,
    var holdLength: String = "30 seconds"
)

interface Exerciseable {
    val stringValue: String
}

enum class Equipment(val value: String): Exerciseable {
    dumbbells("Dumbbells"),
    medicineBall("Medicine Ball"),
    barbell("Barbell"),
    kettlebell("Kettlebell"),
    exerciseBall("Exercise Ball"),
    band("Band"),
    exerciseBike("Exercise Bike"),
    foamRoller("Foam Roller");

    override val stringValue: String
        get() = value
}

enum class ExerciseBodyPart(val value: String): Exerciseable {
    all("All"),
    arms("Arms"),
    ankles("Ankles"),
    feet("Feet"),
    glutes("Glutes"),
    hips("Hips"),
    knees("Knees"),
    shoulders("Shoulders"),
    chest("Chest"),
    lowerBack("Lower Back"),
    neck("Neck"),
    core("Core"),
    upperBack("Upper Back");

    override val stringValue: String
        get() = value
}