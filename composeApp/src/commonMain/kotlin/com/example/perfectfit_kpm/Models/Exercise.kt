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

//val sampleItems = listOf(
//    Exercise(
//        id = 1,
//        name = "Core Medball Twist (Standing)",
//        imageRes = Res.drawable.coremedballtwiststanding,
////        repetitionCount = 10,
//        longDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
//        sets = "3",
//        holdLength = "5 seconds",
//        frequencyOfExercise = "3 per week",
//        equipmentRequired = listOf(Equipment.medicineBall)),
//    Exercise(id = 2,
//        name = "Gastroc Release",
//        imageRes = Res.drawable.gastrocrelease,
//        repetitionCount = 10,
//        longDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
//        sets = 3,
//        holdLength = "5 seconds",
//        frequencyOfExercise = "3 per week",
//        equipmentRequired = listOf(Equipment.band, Equipment.foamRoller)),
//    Exercise(id = 3,
//        name = "Hip Bridge Exercise Ball",
//        imageRes = Res.drawable.hipbridgemedball,
//        repetitionCount = 10,
//        longDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
//        sets = 3,
//        holdLength = "5 seconds",
//        frequencyOfExercise = "3 per week",
//        equipmentRequired = listOf(Equipment.exerciseBall)),
//    Exercise(id = 4,
//        name = "Superman low back Med Ball",
//        imageRes = Res.drawable.supermanlowbackmedball,
//        repetitionCount = 10,
//        longDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
//        sets = 3,
//        holdLength = "5 seconds",
//        frequencyOfExercise = "3 per week",
//        equipmentRequired = listOf(Equipment.exerciseBall)),
//    Exercise(id = 5,
//        name = "Shoulder Stability Med Ball",
//        imageRes = Res.drawable.shoulderstabilitymedball,
//        repetitionCount = 10,
//        longDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
//        sets = 3,
//        holdLength = "5 seconds",
//        frequencyOfExercise = "3 per week",
//        equipmentRequired = listOf(Equipment.exerciseBall, Equipment.kettlebell)),
//    Exercise(id = 6,
//        name = "Oblique Stretch",
//        imageRes = Res.drawable.obleiquestretch,
//        repetitionCount = 10,
//        longDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
//        sets = 3,
//        holdLength = "5 seconds",
//        frequencyOfExercise = "3 per week",
//        equipmentRequired = listOf(Equipment.dumbbells, Equipment.exerciseBall)),
//    Exercise(id = 7,
//        name = "Machine Row incline Bench",
//        imageRes = Res.drawable.machinerowinclinebench,
//        repetitionCount = 10,
//        longDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
//        sets = 3,
//        holdLength = "5 seconds",
//        frequencyOfExercise = "3 per week",
//        equipmentRequired = listOf(Equipment.barbell, Equipment.dumbbells, Equipment.band)),
//    Exercise(id = 8,
//        name = "Kettle Bell get up",
//        imageRes = Res.drawable.kettlebellgetup,
//        repetitionCount = 10,
//        longDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
//        sets = 3,
//        holdLength = "5 seconds",
//        frequencyOfExercise = "3 per week",
//        equipmentRequired = listOf(Equipment.kettlebell)),
//    Exercise(id = 9,
//        name = "Core Stability Banded",
//        imageRes = Res.drawable.corestabilitybanded,
//        repetitionCount = 10,
//        longDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
//        sets = 3,
//        holdLength = "5 seconds",
//        frequencyOfExercise = "3 per week",
//        equipmentRequired = listOf(Equipment.band))
//)