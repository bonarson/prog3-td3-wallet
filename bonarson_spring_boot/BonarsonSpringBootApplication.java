package com.bonarson_dev.bonarson_spring_boot;

import com.bonarson_dev.bonarson_spring_boot.model.Account;
import com.bonarson_dev.bonarson_spring_boot.model.Devise;
import com.bonarson_dev.bonarson_spring_boot.model.Transaction;
import com.bonarson_dev.bonarson_spring_boot.service.TransactionServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Locale;
import java.util.Scanner;


@SpringBootApplication
public class BonarsonSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BonarsonSpringBootApplication.class, args);

        //scanner pour demander a l'utilisateur;
        Scanner scanner = new Scanner(System.in);
        TransactionServiceImpl tran = new TransactionServiceImpl();
       Connection conn= tran.connect_to_db("sprin_boot","postgres","motdepasse");

        System.out.println("======WELCOM!!! INTER YOUR CHOISE=====");
        System.out.println("1-TRANSACTION");
        System.out.println("2-CONSULTAION DU SOLDE PAR DATE ET HEURE");
        System.out.print("votre choix : ");
        int number_choise = scanner.nextInt();
        if (number_choise == 1) {
            System.out.println("TRANSACTION");
            System.out.println("DEVISE");
            System.out.print("devise_name(Ariary/Euro) : ");
            String devise_name = scanner.next().toLowerCase();
            System.out.print("code(EUR/AR) : ");
            String code = scanner.next();

            Devise devise = new Devise(1, devise_name, code);

            System.out.println("===================== TYPE :");
            System.out.println("1-CREDIT");
            System.out.println("2-DEBIT");
            System.out.print("votre choix : ");
            int num_choise = scanner.nextInt();
            if (num_choise == 1) {
                System.out.println("label : ");
                String label =scanner.next();
                System.out.print("montant : ");
                double montant = scanner.nextDouble();
                System.out.println("type : ");
                String type = scanner.next();
                System.out.println("date et heure : ");
                String date_heure =scanner.next();
                Transaction transaction = new Transaction(1, label, montant,type,date_heure);
                tran.insert_tran(conn,"transaction",1,label,montant,type,date_heure);

                Account C1  = new Account(2, "courant", transaction, 10000, devise, devise.getNameDevise());
                transaction.Credit(montant, C1, devise);
                System.out.println(C1);

            } else if (num_choise==2) {
                String label = "debit";
                System.out.print("montant : ");
                double montant = scanner.nextDouble();
                String type = scanner.next();
                System.out.println("date et heure : ");
                String date_heure =scanner.next();
                Transaction transaction = new Transaction(1, label, montant,type,date_heure);
                tran.insert_tran(conn,"transaction",1,label,montant,type,date_heure);
                Account C1  = new Account(2, "courant", transaction, 10000, devise, devise.getNameDevise());
                transaction.Debit(montant, C1, devise);
                System.out.println(C1);


            }


        }

        //Devise devise = new Devise(1,"Ariary","ar");
        //	Transaction transaction = new Transaction(1,"credit",2000,"12:00:00");
        //Transaction transaction2 = new Transaction(1,"DEBIT",2000,"12:00:00");
        //	Account C1 =new Account(2,"courra",transaction,10000,devise,devise.getNameDevise());
        //	transaction.Credit(2000,C1,devise);
        //	transaction2.Debit(1000,C1,devise);
        //	System.out.println(C1);


    }

}
