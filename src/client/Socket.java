/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JOptionPane;

/**
 *
 * @author Dailon
 */
public class Socket {

    public static void conexao(String sentensa) throws InterruptedException{
        try {
            java.net.Socket cliente = new java.net.Socket("10.15.120.64", 90);

            DataOutputStream output = new DataOutputStream(cliente.getOutputStream());
            output.writeUTF(sentensa);

            output.flush();
            output.close();

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
