package client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author Dailon
 */
public class Client {
    
    final static String CLASSE_PESSOA = "1";
    final static String CLASSE_TURMA  = "2";

    final static String TIPO_PESSOA_PROFESSOR = "1";
    final static String TIPO_PESSOA_ALUNO     = "2";
    

    public static void main(String[] args) throws InterruptedException {
        int acao = 0;

        do {
            int classe = getMensagemEscolhaClasse();

            acao = getMensagemAcao();

            switch (acao) {
                case 1: 
                    list(classe); 
                    break;
                case 2: 
                    get(classe);
                    break;
                case 3: 
                    delete(classe);  
                    break;
                case 4: 
                    update(classe); 
                    break;
                case 5: 
                    insert(classe); 
                    break;
            }
        } while (!(acao == 0 || acao == -1));
    }

    private static int getMensagemEscolhaClasse(){
        String[] choices = {"Pessoa", "Turma"};

        return JOptionPane.showOptionDialog(null, "Deseja dar manutencao em qual classe:", "", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, choices, null);
    }

    private static int getMensagemAcao(){
        String[] choices = {"Sair", "Consultar todos", "visualizar", "Excluir", "Alterar", "Incluir"};

        return JOptionPane.showOptionDialog(null, "Escolha uma das opções:", "Manutenção de Pessoas", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, choices, null);
    }

    private static void list(int classe) throws InterruptedException{
        if(classe == 0){
            String request = "LIST" + ";" +CLASSE_PESSOA;

            callConection(request);
        }
        else if(classe == 1){
            String request = "LIST" + ";" + CLASSE_TURMA;

            callConection(request);
        }
    }

    private static void insert(int classe) throws InterruptedException{
        //Se for a classe Pessoa
        if(classe == 0){
            inserePessoa();
        }
        //Se for a classe turma
        else if(classe == 1){
            insereTurma();
        }
    }
    
    private static void inserePessoa() throws InterruptedException{
        String cpf      = JOptionPane.showInputDialog("Digite o CPF:");
        String nome     = JOptionPane.showInputDialog("Digite o Nome:");
        String endereco = JOptionPane.showInputDialog("Digite o Endereço:");
        String turma    = JOptionPane.showInputDialog("Digite a Descricao da turma:");

        String[] opTipoPessoa = {"Professor", "Aluno"};

        int tipoPessoa = JOptionPane.showOptionDialog(null, "A pessoa a ser incluida e um:", "Manutenção de Pessoas", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, opTipoPessoa, null);

        if(tipoPessoa == 0){
            String[] opGraduacao = {"Graduacao", "Mestrado", "Doutorado"};

            int nivelGraduacao = JOptionPane.showOptionDialog(null, "Nivel de graduacao do Professor:", "Professor", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, opGraduacao, null);

            String request = "INSERT" + ";" + CLASSE_PESSOA + ";" +  ";" + cpf + ";" + nome + ";" + endereco + ";" + TIPO_PESSOA_PROFESSOR + ";" + nivelGraduacao + ";" + turma;

            callConection(request);
        }
        else if(tipoPessoa == 1){
            int matricula = Integer.parseInt(JOptionPane.showInputDialog("Digite a Matricula:"));

            String request = "INSERT" + ";" + CLASSE_PESSOA + ";" + cpf + ";" + nome + ";" + endereco + ";" + TIPO_PESSOA_ALUNO + ";" + matricula + ";" + turma;

            callConection(request);
        }
    }
    
    private static void insereTurma() throws InterruptedException{
        String descricao        = JOptionPane.showInputDialog("Descricao:");
        String quantidadeAlunos = JOptionPane.showInputDialog("Quantidade de Alunos:");
        String ano              = JOptionPane.showInputDialog("Ano:");

        String request = "INSERT" + ";" + CLASSE_TURMA + ";" + descricao + ";" + quantidadeAlunos + ";" + ano;

        callConection(request);
    }
  
    private static void update(int classe) throws InterruptedException{
        if(classe == 0){
            String cpf      = JOptionPane.showInputDialog("Digite o CPF da pessoa a ser alterada:");
            String nome     = JOptionPane.showInputDialog("Digite o novo Nome:");
            String endereco = JOptionPane.showInputDialog("Digite o novo Endereço:");

            String request = "UPDATE" + ";" + CLASSE_PESSOA + ";" + cpf + ";" + nome + ";" + endereco;

            callConection(request);
        }
        else if(classe == 1){
            String turma     = JOptionPane.showInputDialog("Digite o codigo da turma a ser alterada:");
            String descricao = JOptionPane.showInputDialog("Digite a nova descricao da Turma:");
            String qtdAlunos = JOptionPane.showInputDialog("Digite a nova quantidade de Alunos:");
            String ano       = JOptionPane.showInputDialog("Digite o novo ano da Turma:");

            String request = "UPDATE" + ";" + CLASSE_TURMA + ";" + turma + ";" + descricao + ";" + qtdAlunos + ";" + ano;

            callConection(request);
        }
    }

    private static void delete(int classe) throws InterruptedException{
        if(classe == 0){
            String cpf = JOptionPane.showInputDialog("Digite o CPF:");

            String request = "DELETE" + ";" + CLASSE_PESSOA + ";" + cpf;
            
            callConection(request);
        }
        else if(classe == 1){
            String turma = JOptionPane.showInputDialog("Digite o codigo da turma:");

            String request = "DELETE" + ";" + CLASSE_TURMA + ";" + turma;

            callConection(request);
        }
    }

    private static void get(int classe) throws InterruptedException{
        if(classe == 0){
            String cpf = JOptionPane.showInputDialog("Digite o CPF:");

            String request = "GET" + ";" + CLASSE_PESSOA + ";" + cpf;

            callConection(request);
        }
        else if(classe == 1){
            String turma = JOptionPane.showInputDialog("Digite o codigo da turma:");

            String request = "GET" + ";" + CLASSE_TURMA + ";" + turma;

            callConection(request);
        }
    }

    private static void callConection(String sentensa) throws InterruptedException{
        try {
            Socket cliente = new Socket("10.15.120.64", 90);

            DataOutputStream output = new DataOutputStream(cliente.getOutputStream());
            output.writeUTF(sentensa);

            output.flush();
            
            InputStream in = cliente.getInputStream();

            byte[] dadosBrutos = new byte[1024];

            int qtdBytesLidos = in.read(dadosBrutos);

            while (qtdBytesLidos >= 0) { // enquanto bytes forem lidos...
                String dadosStr = new String(dadosBrutos, 0, qtdBytesLidos);

                JOptionPane.showMessageDialog(null, dadosStr);
                
                qtdBytesLidos = in.read(dadosBrutos);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}