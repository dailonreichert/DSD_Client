
package client;

import static client.Client._CLASSE_PROFESSOR;
import javax.swing.JOptionPane;

/**
 *
 * @author Dailon
 */
public class TurmaProfessor {

    public static String insert() throws InterruptedException{
        String cpf = JOptionPane.showInputDialog("Digite o CPF do professor:");
        int turma  = Integer.parseInt(JOptionPane.showInputDialog("Digite o código da turma:"));

        String request = "INSERT" + ";" + _CLASSE_PROFESSOR + ";" + cpf + ";" + turma;

        return request;
    }

    public static String delete() throws InterruptedException{
        String cpf = JOptionPane.showInputDialog("Digite o CPF do professor a ser excluído:");
        int turma  = Integer.parseInt(JOptionPane.showInputDialog("Digite o código da turma:"));

        String request = "DELETE" + ";" + _CLASSE_PROFESSOR + ";" + cpf + ";" + turma;

        return request;
    }
}