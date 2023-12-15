import dataBaseConfig.connect_to_db;
import model.Account;
import model.Customer;
import model.Devise;
import model.Transaction;
import service.trasactionImp;

import java.sql.Connection;
import java.util.Scanner;

import static java.time.LocalTime.now;

public class Main {

    public static void main(String[] args) {

        connect_to_db db = new connect_to_db();
        Connection conn = db.conect_db("wallet", "postgres", "motdepasse");
        trasactionImp tran = new trasactionImp();
        //scanner pour demander a l'utilisateur;
        Scanner scanner = new Scanner(System.in);


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

            System.out.println("===================== TYPE Categories :");
            System.out.println("1-salaire");
            System.out.println("2-restaurant");
            System.out.print("votre choix : ");
            int num_choise = scanner.nextInt();
            if (num_choise == 1) {
                System.out.println("label : ");
                String label = scanner.next();
                System.out.print("montant : ");
                double montant = scanner.nextDouble();
                System.out.println("type : ");
                String type = scanner.next();
                String date_heure = "now";
                Transaction transaction = new Transaction(1, label, montant, type, date_heure, 1, 1);

                Account C1 = new Account(1, "couran", transaction, 0, date_heure, devise, type, 1);
                Account C2 = new Account(2, "couran", transaction, 100, date_heure, devise, type, 2);
                Account C3 = new Account(3, "couran", transaction, 5000, date_heure, devise, type, 3);
                tran.insert_tran(conn, "transaction", "account", 2, 2, "courrant", C1.getSolde() + montant, label, montant, type, date_heure, 1, 1);

                //  System.out.println("veuiller entre le numero du compte : ");
                //  Account accountNumber = scanner.findWithinHorizon();
                transaction.Credit(montant, C1, devise);
                System.out.println(C1);

            } else if (num_choise == 2) {
                String label = "debit";
                System.out.print("montant : ");
                double montant = scanner.nextDouble();
                String type = scanner.next();
                System.out.println("date et heure : ");
                String date_heure = scanner.next();
                Transaction transaction = new Transaction(1, label, montant, type, date_heure, 1, 2);
                Account C1 = new Account(1, "couran", transaction, 0, date_heure, devise, type, 1);
                Account C2 = new Account(2, "couran", transaction, 100, date_heure, devise, type, 2);
                Account C3 = new Account(3, "couran", transaction, 5000, date_heure, devise, type, 3);
                tran.insert_tran(conn, "transaction", "account", 1, 1, "courrant", C1.getSolde() + montant, label, montant, type, date_heure, 1, 2);
                transaction.Debit(montant, C1, devise);
                System.out.println(C1);


            }


        }


    }

}
