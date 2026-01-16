package Clinic.Managment.System.Clinic.Managment.System.Model.Enums;


public enum AppointmentStatus {
    PENDING,      // Takimi është krijuar por ende jo i konfirmuar
    CONFIRMED,    // Takimi është konfirmuar
    IN_PROGRESS,  // Takimi është duke u zhvilluar
    COMPLETED,    // Takimi ka përfunduar
    CANCELLED,    // Takimi është anuluar
    NO_SHOW       // Pacienti nuk është paraqitur
}
