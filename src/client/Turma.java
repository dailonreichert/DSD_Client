
package client;

import static client.Client._CLASSE_TURMA;
import javax.swing.JOptionPane;

/**
 *
 * @author Dailon
 */
public class Turma {

    public static String insert() throws InterruptedException{
        String descricao     = JOptionPane.showInputDialog("Descricao:");

        String request = "INSERT" + ";" + _CLASSE_TURMA + ";" + descricao;

        return request;
    }

    public static String update() throws InterruptedException {
        int turma        = Integer.parseInt(JOptionPane.showInputDialog("Digite o código da turma a ser alterada:"));
        String descricao = JOptionPane.showInputDialog("Digite a nova descricao da Turma:");

        String request = "UPDATE" + ";" + _CLASSE_TURMA + ";" + turma + ";" + descricao;

        return request;
    }

    public static String delete() throws InterruptedException{
        String turma = JOptionPane.showInputDialog("Digite o código da turma:");

        String request = "DELETE" + ";" + _CLASSE_TURMA + ";" + turma;

        return request;
    }

    public static String get() throws InterruptedException{
        String turma = JOptionPane.showInputDialog("Digite o código da turma:");

        String request = "GET" + ";" + _CLASSE_TURMA + ";" + turma;

        return request;
    }

    public static String list() throws InterruptedException{
        String request = "LIST" + ";" + _CLASSE_TURMA;

        return request;
    }
}
