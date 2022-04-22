
package client;

import static client.Client._CLASSE_ALUNO;
import javax.swing.JOptionPane;

/**
 *
 * @author Dailon
 */
public class TurmaAluno {
    public static String insert() throws InterruptedException{
        String cpf = JOptionPane.showInputDialog("Digite o CPF do aluno:");
        int turma  = Integer.parseInt(JOptionPane.showInputDialog("Digite o código da turma:"));

        String request = "INSERT" + ";" + _CLASSE_ALUNO + ";" + cpf + ";" + turma;

        return request;
    }
}