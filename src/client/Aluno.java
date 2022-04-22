
package client;

import static client.Client._CLASSE_ALUNO;
import static client.Client._CLASSE_PESSOA;
import javax.swing.JOptionPane;

/**
 *
 * @author Dailon
 */
public class Aluno {

    public static String insert() throws InterruptedException{
        String cpf      = JOptionPane.showInputDialog("Digite o CPF:");
        String nome     = JOptionPane.showInputDialog("Digite o Nome:");
        String endereco = JOptionPane.showInputDialog("Digite o Endereço:");
        int matricula   = Integer.parseInt(JOptionPane.showInputDialog("Digite a Matricula:"));

        String request = "INSERT" + ";" + _CLASSE_ALUNO + ";" + cpf + ";" + nome + ";" + endereco + ";" + matricula;

        return request;
    }

    public static String update() throws InterruptedException {
        String cpf      = JOptionPane.showInputDialog("Digite o CPF do Aluno:");
        String nome     = JOptionPane.showInputDialog("Digite o novo Nome:");
        String endereco = JOptionPane.showInputDialog("Digite o novo Endereço:");
        int matricula   = Integer.parseInt(JOptionPane.showInputDialog("Digite a nova Matricula:"));

        String request = "UPDATE" + ";" + _CLASSE_ALUNO + ";" + cpf + ";" + nome + ";" + endereco + ";" + matricula;

        return request;
    }

    public static String delete() throws InterruptedException{
        String cpf = JOptionPane.showInputDialog("Digite o CPF:");

        String request = "DELETE" + ";" + _CLASSE_PESSOA + ";" + cpf;

        return request;
    }

    public static String get() throws InterruptedException{
        String cpf = JOptionPane.showInputDialog("Digite o CPF:");

        String request = "GET" + ";" + _CLASSE_PESSOA + ";" + cpf;

        return request;
    }

    public static String list() throws InterruptedException{
        String request = "LIST" + ";" + _CLASSE_ALUNO;

        return request;
    }
}
