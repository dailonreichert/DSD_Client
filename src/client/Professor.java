
package client;

import static client.Client.NIVEL_GRADUACAO;
import static client.Client._CLASSE_PESSOA;
import static client.Client._CLASSE_PROFESSOR;
import javax.swing.JOptionPane;

/**
 *
 * @author Dailon
 */
public class Professor {

    public static String insert() throws InterruptedException{
        String cpf      = JOptionPane.showInputDialog("Digite o CPF:");
        String nome     = JOptionPane.showInputDialog("Digite o Nome:");
        String endereco = JOptionPane.showInputDialog("Digite o Endereço:");

        int graduacao = JOptionPane.showOptionDialog(null, "Nível de graduação:", "Professor", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, NIVEL_GRADUACAO, null);

        String request = "INSERT" + ";" + _CLASSE_PROFESSOR + ";" + cpf + ";" + nome + ";" + endereco + ";" + graduacao;

        return request;
    }

    public static String update() throws InterruptedException{
        String cpf      = JOptionPane.showInputDialog("Digite o CPF do Professor:");
        String nome     = JOptionPane.showInputDialog("Digite o novo Nome:");
        String endereco = JOptionPane.showInputDialog("Digite o novo Endereço:");

        int graduacao = JOptionPane.showOptionDialog(null, "Atualize a graduação:", "", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, NIVEL_GRADUACAO, null);

        String request = "UPDATE" + ";" + _CLASSE_PROFESSOR + ";" + cpf + ";" + nome + ";" + endereco + ";" + graduacao;

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
        String request = "LIST" + ";" + _CLASSE_PROFESSOR;

        return request;
    }
}
