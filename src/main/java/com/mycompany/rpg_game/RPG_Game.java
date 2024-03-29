/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.rpg_game;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author joaoa
 */
public class RPG_Game {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Random rand = new Random();
        Player p1 = new Player();

        //Variaveis do jogo
        String[] inimigos = {"Esqueleto", "Zombie", "Mago das trevas", "Assasino", "Dragão"};
        int vidaMaxInimigos = 75;
        int danoAtackInimigo = 25;
        int inimigosDerrotados = 0;
        String[] mercanteItens = {"Poção de vida", "Espada de ferro", "Escudo de madeira refoçada", "Capa mágica de youngmorth"};

        System.out.println("Bem vindo a Masmorra aventureiro!");
        System.out.println("Qual o seu nome? ");
        p1.nome = in.nextLine();
        System.out.println("SEJA MUITO BEM VINDO " + p1.nome + " A SUA AVENTURA!");
        GAME:
        while (p1.correndo) {
            System.out.println("-------------------------------------------");

            System.out.println("\t# O que deseja fazer?");

            System.out.println("\t1.Entrar no corredor da esquerda");
            System.out.println("\t2. Entrar no corredor da direita");
            System.out.println("\t3. Seguir no corredor reto");

            String caminho = in.nextLine();

            if (rand.nextInt(100) <= 5) {
                System.out.println("Você encontrou mais corredores!");
            } else if (rand.nextInt(100) > 6 && rand.nextInt(100) <= 15) {
                System.out.println("\t Olá caro viajante, eu sou o Mercante venha e compre comigo!");
                System.out.printf("\t# Você possui %d moedas de ouro\n", p1.moedas);
                System.out.println("\t# Escolha o que quiser");
                System.out.println("\t# 1. Poção de vida");
                System.out.println("\t# 2. Espada de ferro");
                System.out.println("\t# 3. Escudo de madeira reforçada");
                System.out.println("\t# 4. Capa mágica de youngmorth");
                System.out.println("\t# 5. SAIR");

                String opcao = in.nextLine();
                switch(opcao){
                    case "1":
                        if (p1.moedas >= 2) {
                            p1.numPocaoVida++;
                            System.out.println("Parabéns você adiquiriu " + mercanteItens[0]);
                            System.out.printf("Agora você possui %d poções\n", p1.numPocaoVida);
                            p1.moedas -= 2;
                        } else {
                            System.out.println("Você não tem dinheiro volte mais tarde!");
                        }
                        break;
                    case "2":
                        if (p1.moedas >= 10) {
                            p1.dano = 70;
                            System.out.println("Parabéns você adiquiriu " + mercanteItens[1]);
                            p1.moedas -= 10;
                        } else {
                            System.out.println("Você não tem dinheiro volte mais tarde!");
                        }
                        break ;
                    case "3":
                         if (p1.moedas >= 8) {
                            danoAtackInimigo = 15;
                            System.out.println("Parabéns você adiquiriu " + mercanteItens[2]);
                            p1.moedas -= 8;
                        }else {
                             System.out.println("Você não tem dinheiro volte mais tarde!");
                         }
                         break;
                    case "4":
                        if (p1.moedas >= 8) {
                            p1.vida = 130;
                            System.out.println("Parabéns você adiquiriu " + mercanteItens[3]);
                            System.out.printf("Agora você tem %d de vida", p1.vida);
                            p1.moedas -= 8;
                        } else {
                            System.out.println("Você não tem dinheiro volte mais tarde!");
                        }
                        break;
                    case "5":
                        System.out.println("Até a próxima querido aventureiro");
                        break;

                    default:
                        System.out.println("A masmorra ta mechendo com você! Essa opção não existe");

                }
            } else if (rand.nextInt(100) > 15 && rand.nextInt(100) <= 30) {
                int moedasGanhas = rand.nextInt(5);
                p1.moedas += moedasGanhas;
                System.out.println("Hoje é seu dia de sorte você conseguiu " + moedasGanhas + " moedas \n"
                        + " Agora você tem " + p1.moedas);
            } else if (rand.nextInt(100) >= 31) {
                int vidaInimigo = rand.nextInt(vidaMaxInimigos);
                String inimigo = inimigos[rand.nextInt(inimigos.length)];
                System.out.println("\t# " + inimigo + " te encontrou! #\n");

                while (vidaInimigo > 0) {
                    System.out.printf("\t %s  HP: %d\n", p1.nome, p1.vida);
                    System.out.printf("\t %s HP: %d\n", inimigo, vidaInimigo);
                    System.out.printf("\tO que %s ira fazer? \n", p1.nome);
                    System.out.println("\t1. Atacar");
                    System.out.println("\t2. Beber Poção");
                    System.out.println("\t3. Fugir");

                    String input = in.nextLine();
                    switch (input){
                        case "1":
                            int danoDado = rand.nextInt(p1.dano);
                            int danoSofrido = rand.nextInt(danoAtackInimigo);

                            vidaInimigo -= danoDado;
                            p1.vida -= danoSofrido;

                            System.out.printf("\t->Você acertou o %s e deu %d de dano\n", inimigo, danoDado);
                            System.out.printf("\t->Você recebeu %d na luta\n", danoSofrido);

                            if (p1.vida < 1) {
                                System.out.println("\tVocê tomou muito dano, melhor fugir!");
                                break;
                            }
                            break;
                        case "2":
                            if (p1.numPocaoVida > 0) {
                                p1.vida += p1.curaPocao;
                                p1.numPocaoVida--;
                                System.out.printf("""
                                    \tVocê bebeu a poção e curou: %d
                                    \tAgora você tem %d HP
                                    \t Agora você tem %d poções
                                    """, p1.curaPocao, p1.vida, p1.numPocaoVida);
                            } else {
                                System.out.println("\tVocê não tem pocoes! Derrote o inimigo para conseguir!");
                            }
                            break;
                        case "3":
                            System.out.println("\t Você fugiu do " + inimigo);
                            continue GAME;
                        default:
                            System.out.println("Os deuses não permitiram usar esse comando estranho!");
                    }
                }
                if (p1.vida < 1) {
                    System.out.println("Você sofreu muitos danos, vá para casa e descanse");
                    System.out.printf("Você derrotou %d inimigos poderosos!\n", inimigos);
                    break;
                }

                System.out.println("-------------------------------------------");
                System.out.printf(" # %s foi derrotado!", inimigo);
                System.out.printf(" # Você tem %d HP sobrando!", p1.vida);
                inimigosDerrotados++;
                if (rand.nextInt(100) < p1.chancePocao) {
                    p1.numPocaoVida++;
                    System.out.println(" # Você conseguiu encontrar uma poção de vida no corpo do " + inimigo);
                    System.out.println(" # Agora voce tem " + p1.numPocaoVida + " poções");
                }
                if (rand.nextInt(100) < 50) {
                    p1.moedas += 2;
                    System.out.println(" # Você conseguiu encontrar duas moedas no corpo do " + inimigo);
                    System.out.println(" # Agora você tem " + p1.moedas + " moedas");
                }

                System.out.println("O que voce quer fazer agora? ");
                System.out.println("1. Continuar explorando");
                System.out.println("2. Sair da Masmorra");

                String input = in.nextLine();

                while (!input.equals("1") && !input.equals("2")) {
                    System.out.println("A masmorra mexeu com sua cabeca? Opcao invalida");
                    input = in.nextLine();
                }

                if (input.equals("1")) {
                    System.out.println("Voce e corajoso seguira a sua aventura");
                } else if (input.equals("2")) {
                    System.out.println("Você se aventurou e conquistou sua glória! Hora de ir pra casa");
                    System.out.printf("Você derroutou %d inimigos poderosos!\n", inimigosDerrotados);
                    break;
                }
            }

        }

        System.out.println("######################");
        System.out.println("# Obrigado por jogar #");
        System.out.println("######################");
    }
}
