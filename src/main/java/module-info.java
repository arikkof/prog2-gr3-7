// was brauchen wir in unserer Demo-App?
module at.ac.fhcampuswien.fhmdb {
    requires javafx.controls;
    requires javafx.fxml;
    requires ormlite.jdbc;

    requires com.jfoenix;
    requires okhttp3;
    requires com.google.gson;
    requires java.sql; // !!!! selber einfuegen
    opens at.ac.fhcampuswien.fhmdb.models to com.google.gson;

    opens at.ac.fhcampuswien.fhmdb to javafx.fxml;
    exports at.ac.fhcampuswien.fhmdb;
    exports at.ac.fhcampuswien.fhmdb.models;

    opens at.ac.fhcampuswien.fhmdb.data to ormlite.jdbc;
    requires com.h2database;
    exports at.ac.fhcampuswien.fhmdb.data;
    exports at.ac.fhcampuswien.fhmdb.contoller;
    opens at.ac.fhcampuswien.fhmdb.contoller to com.google.gson, javafx.fxml;
    exports at.ac.fhcampuswien.fhmdb.states;
    opens at.ac.fhcampuswien.fhmdb.states to com.google.gson;

}