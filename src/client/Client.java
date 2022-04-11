package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author Dailon
 */
public class Client {

    public static void main(String[] args) throws InterruptedException {
        int option = 0;
        
        JOptionPane.showMessageDialog(null, "Teste");

        do {
            int optionClass = messageOptionClass();

            option = messageOption();

            switch (option) {
                case 0: 
                    list(optionClass); 
                    break;
                case 1: 
                    insert(optionClass);  
                    break;
                case 2: 
                    update(optionClass);  
                    break;
                case 3: 
                    delete(optionClass); 
                    break;
                case 4: 
                    get(optionClass); 
                    break;
            }
        } while (!(option == 5 || option == -1));
    }

    private static int messageOptionClass(){
        String[] choices = {"Pessoa", "Turma"};

        return JOptionPane.showOptionDialog(null, "Deseja dar manutencao em qual classe:", "", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, choices, "Botao 3");
    }

    private static int messageOption(){
        String[] choices = {"Consultar todos", "Incluir", "Alterar", "Excluir", "viszualizar", "Sair"};

        return JOptionPane.showOptionDialog(null, "Escolha uma das opções:", "Manutenção de Pessoas", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, choices, "Botao 3");
    }

    private static void list(int optionClass) throws InterruptedException{
        String[] request = {"LIST" + optionClass};

        callConection(request);
    }

    private static void insert(int optionClass) throws InterruptedException{
        if(optionClass == 0){
            String cpf      = JOptionPane.showInputDialog("Digite o CPF:");
            String nome     = JOptionPane.showInputDialog("Digite o Nome:");
            String endereco = JOptionPane.showInputDialog("Digite o Endereço:");

            String[] opTipoPessoa = {"Professor", "Aluno"};

            int tipoPessoa = JOptionPane.showOptionDialog(null, "A pessoa a ser incluida e um:", "Manutenção de Pessoas", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, opTipoPessoa, "Botao 3");

            if(tipoPessoa == 0){
                String[] opGraduacao = {"Graduacao", "Mestrado", "Doutorado"};

                int nivelGraduacao = JOptionPane.showOptionDialog(null, "Nivel de graduacao do Professor:", "Professor", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, opGraduacao, "Botao 3");
                
                String[] request = {"INSERT" + ";" + optionClass +  ";" + cpf + ";" + nome + ";" + endereco + ";" + tipoPessoa + ";" + nivelGraduacao};

                callConection(request);
            }
            else if(tipoPessoa == 1){
                int matricula = Integer.parseInt(JOptionPane.showInputDialog("Digite a Matricula:"));
                
                String[] request = {"INSERT" + ";" + optionClass +  ";" + cpf + ";" + nome + ";" + endereco + ";" + tipoPessoa + ";" + matricula};

                callConection(request);
            }
            
        }
        else if(optionClass == 1){
            String descricao        = JOptionPane.showInputDialog("Descricao:");
            String quantidadeAlunos = JOptionPane.showInputDialog("Quantidade de Alunos:");
            String ano              = JOptionPane.showInputDialog("Ano:");

            String[] request = {"INSERT" + ";" + optionClass + ";" + descricao + ";" + quantidadeAlunos + ";" + ano};
 
            callConection(request);
        }
    }
  
    private static void update(int optionClass) throws InterruptedException{
        String cpf      = JOptionPane.showInputDialog("Digite o CPF da pessoa a ser alterada:");
        String nome     = JOptionPane.showInputDialog("Digite o novo Nome:");
        String endereco = JOptionPane.showInputDialog("Digite o novo Endereço:");

        String[] request = {"UPDATE" + ";" + cpf + ";" + nome + ";" + endereco};

        callConection(request);
    }

    private static void delete(int optionClass) throws InterruptedException{
        String cpf = JOptionPane.showInputDialog("Digite o CPF:");
        
        String[] request = {"DELETE" + ";" + cpf};

        callConection(request);
    }

    private static void get(int optionClass) throws InterruptedException{
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
            
            InputStream in = cliente.getInputStream();

            byte[] dadosBrutos = new byte[1024];

            int qtdBytesLidos = in.read(dadosBrutos);

            while (qtdBytesLidos >= 0) { // enquanto bytes forem lidos...
                String dadosStr = new String(dadosBrutos, 0, qtdBytesLidos);
                System.out.println(dadosStr);
                //JOptionPane.showMessageDialog(parentComponent, cliente);
                qtdBytesLidos = in.read(dadosBrutos);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}