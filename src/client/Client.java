package client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author Dailon
 */
public class Client {

    public static void main(String[] args) throws InterruptedException {
        int option = messageOption();
        
        switch (option) {
            case 0: 
                list(); 
                break;
            case 1: 
                insert();  
                break;
            case 2: 
                update();  
                break;
            case 3: 
                delete(); 
                break;
            case 4: 
                get(); 
                break;
        }
    }

    private static int messageOption(){
        String[] choices = {"CONSULTAR TODOS", "INCLUIR", "ALTERAR", "EXCLUIR", "VISUALIZAR", "SAIR"};

        int option = JOptionPane.showOptionDialog(null, "Escolha uma das opções:", "Manutenção de Pessoas", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, choices, "Botao 3");

        return option;
    }

    private static void list() throws InterruptedException{
        String[] request = {"LIST"};

        callConection(request);
    }

    private static void insert() throws InterruptedException{
        String cpf      = JOptionPane.showInputDialog("Digite o CPF:");
        String nome     = JOptionPane.showInputDialog("Digite o Nome:");
        String endereco = JOptionPane.showInputDialog("Digite o Endereço:");

        String[] opTipoPessoa = {"Professor", "Aluno"};

        int tipoPessoa = JOptionPane.showOptionDialog(null, "A pessoa a ser incluida e um:", "Manutenção de Pessoas", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, opTipoPessoa, "Botao 3");

        int teste = 0;
        
        if(tipoPessoa == 0){
            String[] opGraduacao = {"Graduacao", "Mestrado", "Doutorado"};

            teste = JOptionPane.showOptionDialog(null, "Nivel de graduacao do Professor:", "Professor", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, opGraduacao, "Botao 3");
        }
        else if(tipoPessoa == 1){
            teste = Integer.parseInt(JOptionPane.showInputDialog("Digite a Matricula:"));
        }
        
        String[] request = {"INSERT" + ";" + cpf + ";" + nome + ";" + endereco + ";" + tipoPessoa + ";" + teste};

        callConection(request);
    }
  
    private static void update() throws InterruptedException{
        String cpf      = JOptionPane.showInputDialog("Digite o CPF da pessoa a ser alterada:");
        String nome     = JOptionPane.showInputDialog("Digite o novo Nome:");
        String endereco = JOptionPane.showInputDialog("Digite o novo Endereço:");

        String[] request = {"UPDATE" + ";" + cpf + ";" + nome + ";" + endereco};

        callConection(request);
    }

    private static void delete() throws InterruptedException{
        String cpf = JOptionPane.showInputDialog("Digite o CPF:");
        
        String[] request = {"DELETE" + ";" + cpf};

        callConection(request);
    }

    private static void get() throws InterruptedException{
        String cpf = JOptionPane.showInputDialog("Digite o CPF:");
        
        String[] request = {"GET" + ";" + cpf};

        callConection(request);
    }
    
    private static void callConection(String[] sentensa) throws InterruptedException{
        try {
            Socket cliente = new Socket("10.15.120.59", 80);
            
            ObjectOutputStream output = new ObjectOutputStream(cliente.getOutputStream());
            
            for (int i = 0; i < sentensa.length; i++) {
                Thread.sleep(1000);
                output.write(sentensa[i].getBytes());
            }

            output.flush();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}