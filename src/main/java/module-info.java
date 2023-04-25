// was brauchen wir in unserer Demo-App?
module at.ac.fhcampuswien.fhmdb {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.jfoenix;
    requires okhttp3;
    requires com.google.gson; // !!!! selber einfuegen
    opens at.ac.fhcampuswien.fhmdb.models to com.google.gson;

    opens at.ac.fhcampuswien.fhmdb to javafx.fxml;
    exports at.ac.fhcampuswien.fhmdb;
    exports at.ac.fhcampuswien.fhmdb.models;

    requires ormlite.jdbc;
    requires java.sql;
    opens at.ac.fhcampuswien.fhmdb.database to ormlite.jdbc;
    requires com.h2database;
}