//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class EmployeeManagementSystem {
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin";
    private static ArrayList<String> employees = new ArrayList();
    private static HashMap<String, String> employeeCredentials = new HashMap();

    public EmployeeManagementSystem() {
    }

    public static void main(String[] args) {
        initializeEmployees();
        Scanner scanner = new Scanner(System.in);
        if (!attemptLogin(scanner)) {
            System.out.println("Nieudane logowanie. Program zostanie zamknięty.");
            scanner.close();
        } else {
            boolean isRunning = true;

            while(isRunning) {
                System.out.println("\n=== MENU GŁÓWNE ===");
                System.out.println("1. Wyświetl listę pracowników");
                System.out.println("2. Zakończ działanie programu");
                System.out.print("Wybierz opcję: ");
                int mainChoice = scanner.nextInt();
                scanner.nextLine();
                switch (mainChoice) {
                    case 1:
                        displayEmployees(scanner);
                        break;
                    case 2:
                        System.out.println("Zakończono działanie programu.");
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
                }
            }

            scanner.close();
        }
    }

    private static void initializeEmployees() {
        employees.add("Adam Nowacki");
        employees.add("Ola Kos");
        employeeCredentials.put("Adam Nowacki", "Adam Nowacki");
        employeeCredentials.put("Ola Kos", "Ola Kos");
    }

    private static boolean attemptLogin(Scanner scanner) {
        int MAX_ATTEMPTS = 3;
        int attempts = 0;

        while(attempts < 3) {
            System.out.println("=== SYSTEM LOGOWANIA ===");
            System.out.print("Podaj nazwę użytkownika: ");
            String enteredUsername = scanner.nextLine();
            System.out.print("Podaj hasło: ");
            String enteredPassword = scanner.nextLine();
            if ("admin".equals(enteredUsername) && "admin".equals(enteredPassword)) {
                System.out.println("Logowanie zakończone sukcesem! Witaj, " + enteredUsername + ".");
                return true;
            }

            if (employeeCredentials.containsKey(enteredUsername) && ((String)employeeCredentials.get(enteredUsername)).equals(enteredPassword)) {
                System.out.println("Logowanie zakończone sukcesem! Witaj, " + enteredUsername + ".");
                return true;
            }

            ++attempts;
            System.out.println("Nieprawidłowa nazwa użytkownika lub hasło. Próba " + attempts + " z 3.");
        }

        System.out.println("Przekroczono maksymalną liczbę prób logowania.");
        return false;
    }

    private static void displayEmployees(Scanner scanner) {
        System.out.println("\n=== LISTA PRACOWNIKÓW ===");
        if (employees.isEmpty()) {
            System.out.println("Lista pracowników jest pusta.");
        } else {
            for(int i = 0; i < employees.size(); ++i) {
                System.out.println(i + 1 + ". " + (String)employees.get(i));
            }
        }

        System.out.println("\n=== ZARZĄDZANIE PRACOWNIKAMI ===");
        System.out.println("1. Dodaj pracownika");
        System.out.println("2. Edytuj pracownika");
        System.out.println("3. Usuń pracownika");
        System.out.println("4. Powrót do menu głównego");
        System.out.print("Wybierz opcję: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1 -> addEmployee(scanner);
            case 2 -> editEmployee(scanner);
            case 3 -> deleteEmployee(scanner);
            case 4 -> System.out.println("Powrót do menu głównego.");
            default -> System.out.println("Nieprawidłowy wybór. Powrót do menu głównego.");
        }

    }

    private static void addEmployee(Scanner scanner) {
        System.out.print("Podaj imię i nazwisko nowego pracownika: ");
        String newEmployee = scanner.nextLine();
        employees.add(newEmployee);
        employeeCredentials.put(newEmployee, newEmployee);
        System.out.println("Dodano pracownika: " + newEmployee);
    }

    private static void editEmployee(Scanner scanner) {
        if (employees.isEmpty()) {
            System.out.println("Lista pracowników jest pusta.");
        } else {
            System.out.println("Lista pracowników:");

            for(int i = 0; i < employees.size(); ++i) {
                System.out.println(i + 1 + ". " + (String)employees.get(i));
            }

            System.out.print("Podaj numer pracownika do edycji: ");
            int index = scanner.nextInt() - 1;
            scanner.nextLine();
            if (index >= 0 && index < employees.size()) {
                String oldName = (String)employees.get(index);
                System.out.print("Podaj nowe imię i nazwisko: ");
                String updatedName = scanner.nextLine();
                employees.set(index, updatedName);
                employeeCredentials.remove(oldName);
                employeeCredentials.put(updatedName, updatedName);
                System.out.println("Zaktualizowano pracownika na: " + updatedName);
            } else {
                System.out.println("Nieprawidłowy numer pracownika.");
            }

        }
    }

    private static void deleteEmployee(Scanner scanner) {
        if (employees.isEmpty()) {
            System.out.println("Lista pracowników jest pusta.");
        } else {
            System.out.println("Lista pracowników:");

            for(int i = 0; i < employees.size(); ++i) {
                System.out.println(i + 1 + ". " + (String)employees.get(i));
            }

            System.out.print("Podaj numer pracownika do usunięcia: ");
            int index = scanner.nextInt() - 1;
            scanner.nextLine();
            if (index >= 0 && index < employees.size()) {
                String removedEmployee = (String)employees.remove(index);
                employeeCredentials.remove(removedEmployee);
                System.out.println("Usunięto pracownika: " + removedEmployee);
            } else {
                System.out.println("Nieprawidłowy numer pracownika.");
            }

        }
    }
}
