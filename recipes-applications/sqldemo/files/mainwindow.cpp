#include "mainwindow.h"
#include "ui_mainwindow.h"

#include <QTime>
#include <QDebug>
#include <QSqlError>

MainWindow::MainWindow(QWidget *parent) : QMainWindow(parent), ui(new Ui::MainWindow) {
    ui->setupUi(this);

    QStringList dbDrivers = QSqlDatabase::drivers();
    ui->sbDrivers->insertItems(0, dbDrivers);

    connect(ui->pbConnect, &QPushButton::clicked, this, &MainWindow::ConnectDatabase);
    connect(ui->pbDisconnect, &QPushButton::clicked, this, &MainWindow::CloseDatabase);
}

bool MainWindow::ConnectDatabase() {
    bool ok = false;
    QSqlDatabase db = QSqlDatabase::addDatabase(ui->sbDrivers->currentText());
    db.setConnectOptions("SQL_ATTR_LOGIN_TIMEOUT=5");
    db.setConnectOptions("SQL_ATTR_CONNECTION_TIMEOUT=5");
    db.setConnectOptions("MYSQL_OPT_CONNECT_TIMEOUT=5");

    db.setHostName(ui->leServer->text());
    db.setDatabaseName(ui->leDatabase->text());
    db.setUserName(ui->leUser->text());
    db.setPassword(ui->lePassword->text());
    ok = db.open();

    if (!ok) {
        ui->laStatus->setText("getrennt");
        QSqlError error = db.lastError();
        qDebug() << "DatabaseText:" << error.databaseText();
        qDebug() << "DriverText:" << error.driverText();
    } else {
        qDebug() << "Database connected";
        ui->laStatus->setText("verbunden");
    }

    ui->pbConnect->setEnabled(!ok);
    ui->pbDisconnect->setEnabled(ok);

    return ok;
}

bool MainWindow::CloseDatabase() {
    bool ok = false;
    QSqlDatabase db = QSqlDatabase::database();

    if (db.isOpen()) {
        qDebug() << "Disconnect";
        db.close();
    }

    ok = !db.isOpen();

    if (ok) {
        qDebug() << "getrennt";
        QSqlDatabase::removeDatabase(db.connectionName());

        ui->laStatus->setText("getrennt");
        ui->pbConnect->setEnabled(true);
        ui->pbDisconnect->setEnabled(false);
    } else {
        ui->laStatus->setText("verbunden");
        ui->pbConnect->setEnabled(false);
        ui->pbDisconnect->setEnabled(true);
    }

    return ok;
}

MainWindow::~MainWindow() {
    delete ui;
}

