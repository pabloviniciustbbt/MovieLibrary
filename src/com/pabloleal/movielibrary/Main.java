package com.pabloleal.movielibrary;

import com.pabloleal.movielibrary.models.Movie;
import com.pabloleal.movielibrary.services.JsonServices;
import com.pabloleal.movielibrary.services.ApiServices;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ApiServices apiServices = new ApiServices();
        JsonServices jsonServices = new JsonServices();
        Movie movie;
        Scanner scan = new Scanner(System.in);
        String movieInput;
        int mainMenu = -1;
        int subMenu = -1;
        String confirm;
        List<Movie> assistido = new ArrayList<>();
        List<Movie> queroAssistir = new ArrayList<>();


        System.out.println("\n=================================");
        System.out.println("          Movie Library");
        System.out.println("=================================");

        //Loop Menu Principal
        while (mainMenu != 0){

            System.out.println("\nEscolha uma das opcoes abaixo:");
            System.out.println("\n1. Pesquisar Filme");
            System.out.println("2. Salvar lista em Json");
            System.out.println("3. Limpar listas");
            System.out.println("0. Sair");
            System.out.print("\nDigite aqui: ");

            try{
                mainMenu = scan.nextInt();
                scan.nextLine();
            } catch (InputMismatchException e){
                System.out.println("\n==============================================");
                System.out.println("              Entrada Invalida");
                System.out.println("==============================================");
                scan.nextLine();
                continue;
            }

            switch (mainMenu){
                case 0:
                    System.out.println("\nSaindo...");
                    break;
                case 1:
                    String result;
                    System.out.print("\nDigite o nome de um filme: ");
                    movieInput = scan.nextLine();

                    if (movieInput.trim().isEmpty()){
                        System.out.println("\n==============================================");
                        System.out.println("              Entrada Invalida");
                        System.out.println("==============================================");
                        continue;
                    }

                    movieInput = apiServices.textFormatting(movieInput);

                    try {
                        result = apiServices.movieRequest(movieInput);

                        if (result == null){
                            continue;
                        }

                        movie = jsonServices.jsonConverter(result);
                        System.out.println(movie);

                        while (subMenu != 0){
                            System.out.println("\nEscolha uma das opcoes abaixo:");
                            System.out.println("\n1. Adicionar filme a lista Assistidos");
                            System.out.println("2. Adicionar filme a lista Quero Assistir");
                            System.out.println("0. Voltar ao Menu Principal");
                            System.out.print("\nDigite Aqui: ");

                            try{
                                subMenu = scan.nextInt();
                                scan.nextLine();
                            } catch (InputMismatchException e){
                                System.out.println("\n==============================================");
                                System.out.println("              Entrada Invalida");
                                System.out.println("==============================================");
                                scan.nextLine();
                                continue;
                            }

                            switch (subMenu){
                                case 1:
                                   if(assistido.contains(movie)){
                                        System.out.println("\n==============================================");
                                        System.out.println("  Esse filme já está na lista Assistidos");
                                        System.out.println("==============================================");
                                        break;
                                    }

                                    assistido.add(movie);
                                    System.out.println("\n==============================================");
                                    System.out.println("      Filme Adicionado a Lista Assistidos");
                                    System.out.println("==============================================");
                                    break;
                                case 2:
                                    if (queroAssistir.contains(movie)){
                                        System.out.println("\n==============================================");
                                        System.out.println("  Esse filme ja está na lista Quero Assistir");
                                        System.out.println("==============================================");
                                        break;
                                    }
                                    queroAssistir.add(movie);
                                    System.out.println("\n==============================================");
                                    System.out.println("    Filme Adicionado a Lista Quero Assistir");
                                    System.out.println("==============================================");
                                    break;
                                case 0:
                                    break;
                                default:
                                    System.out.println("\nOpção Invalida");
                            }
                            break;
                        }
                        subMenu = -1;

                    } catch (Exception e){
                        System.out.println(e.getMessage());
                        continue;
                    }

                    break;
                case 2:
                    while (subMenu != 0){
                        System.out.println("\nEscolha uma das opcoes abaixo:");
                        System.out.println("\n1. Salvar lista Assistido");
                        System.out.println("2. Salvar lista Quero Assistir");
                        System.out.println("3. Salvar todas as listas");
                        System.out.println("0. Voltar ao Menu Principal");
                        System.out.print("\nDigite Aqui: ");

                        try{
                            subMenu = scan.nextInt();
                            scan.nextLine();
                        } catch (InputMismatchException e){
                            System.out.println("\n==============================================");
                            System.out.println("              Entrada Invalida");
                            System.out.println("==============================================");
                            scan.nextLine();
                            continue;
                        }

                        switch (subMenu){
                            case 0:
                                break;
                            case 1:
                                if (assistido.isEmpty()){
                                    System.out.println("\n============================================");
                                    System.out.println("        Não foi possível salvar o " +
                                            "\n      arquivo pois a lista esta vazia!");
                                    System.out.println("============================================");
                                    break;
                                }
                                jsonServices.jsonWriter(assistido,"assistidos");
                                break;
                            case 2:
                                if (queroAssistir.isEmpty()){
                                    System.out.println("\n============================================");
                                    System.out.println("        Não foi possível salvar o " +
                                            "\n      arquivo pois a lista esta vazia!");
                                    System.out.println("============================================");
                                    break;
                                }
                                jsonServices.jsonWriter(queroAssistir,"quero_assistir");
                                break;
                            case 3:
                                if (assistido.isEmpty() || queroAssistir.isEmpty()){
                                    System.out.println("\n============================================");
                                    System.out.println("   Não foi possível salvar os arquivos, " +
                                            "\n     pois uma das listas estao vazias");
                                    System.out.println("============================================");
                                    break;
                                }
                                jsonServices.jsonWriter(assistido,"assistidos");
                                jsonServices.jsonWriter(queroAssistir,"quero_assistir");
                                break;
                        }
                    }
                    subMenu = -1;
                    break;
                case 3:
                    while (subMenu != 0) {
                        System.out.println("\nEscolha uma das opcoes abaixo:");
                        System.out.println("\n1. limpar lista Assistido");
                        System.out.println("2. limpar lista Quero Assistir");
                        System.out.println("3. limpar todas as listas");
                        System.out.println("0. Voltar ao Menu Principal");
                        System.out.print("\nDigite Aqui: ");

                        try{
                            subMenu = scan.nextInt();
                            scan.nextLine();
                        } catch (InputMismatchException e){
                            System.out.println("\n==============================================");
                            System.out.println("              Entrada Invalida");
                            System.out.println("==============================================");
                            scan.nextLine();
                            continue;
                        }

                        switch (subMenu) {
                            case 0:
                                break;
                            case 1:
                                System.out.print("\nTem certeza que deseja limpar essa lista? (s/n): ");
                                confirm = scan.nextLine();
                                if (confirm.equalsIgnoreCase("s")){
                                    assistido.clear();
                                    System.out.println("\n============================================");
                                    System.out.println("      A lista foi limpa com sucesso!");
                                    System.out.println("============================================");
                                    break;
                                } else if (confirm.equalsIgnoreCase("n")){
                                System.out.println("\n============================================");
                                System.out.println("        Acao cancelada pelo usuario!");
                                System.out.println("============================================");
                                break;
                            } else {
                                    System.out.println("\n==============================================");
                                    System.out.println("              Entrada Invalida");
                                    System.out.println("==============================================");
                                }

                            case 2:
                                System.out.print("\nTem certeza que deseja limpar essa lista? (s/n): ");
                                confirm = scan.nextLine();
                                if (confirm.equalsIgnoreCase("s")){
                                    queroAssistir.clear();
                                    System.out.println("\n============================================");
                                    System.out.println("      A lista foi limpa com sucesso!");
                                    System.out.println("============================================");
                                    break;
                                } else if (confirm.equalsIgnoreCase("n")){
                                    System.out.println("\n============================================");
                                    System.out.println("        Acao cancelada pelo usuario!");
                                    System.out.println("============================================");
                                    break;
                                } else {
                                    System.out.println("\n==============================================");
                                    System.out.println("              Entrada Invalida");
                                    System.out.println("==============================================");
                                }
                            case 3:
                                System.out.print("\nTem certeza que deseja limpar todas as listas? (s/n): ");
                                confirm = scan.nextLine();
                                if (confirm.equalsIgnoreCase("s")){
                                    assistido.clear();
                                    queroAssistir.clear();
                                    System.out.println("\n============================================");
                                    System.out.println("    As listas foram limpas com sucesso!");
                                    System.out.println("============================================");
                                    break;
                                }
                                else if (confirm.equalsIgnoreCase("n")){
                                    System.out.println("\n============================================");
                                    System.out.println("        Acao cancelada pelo usuário!");
                                    System.out.println("============================================");
                                    break;
                                } else {
                                    System.out.println("\n==============================================");
                                    System.out.println("              Entrada Invalida");
                                    System.out.println("==============================================");
                                }
                        }
                    }
                    subMenu = -1;
                    break;
                default:
                    System.out.println("\nOpcao Invalida");
            }
        }
    }
}
