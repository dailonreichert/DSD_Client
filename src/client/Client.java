package client;

import javax.swing.JOptionPane;

/**
 *
 * @author Dailon
 */
public class Client {

    final static int BOTAO_FECHAR = -1;

    final static int CLASSE_PROFESSOR      = 0;
    final static int CLASSE_ALUNO          = 1;
    final static int CLASSE_TURMA          = 2;
    final static int CLASSE_TURMA_ALUNO    = 3;
    final static int CLASSE_TURMA_PRFESSOR = 4;

    final static String _CLASSE_PESSOA         = "Pessoa";
    final static String _CLASSE_PROFESSOR      = "Professor";
    final static String _CLASSE_ALUNO          = "Aluno";
    final static String _CLASSE_TURMA          = "Turma";
    final static String _CLASSE_TURMA_ALUNO    = "Turma X Aluno";
    final static String _CLASSE_TURMA_PRFESSOR = "Turma X Professor";

    final static String[] CLASSES = {_CLASSE_PROFESSOR, _CLASSE_ALUNO, _CLASSE_TURMA, _CLASSE_TURMA_ALUNO, _CLASSE_TURMA_PRFESSOR};

    final static int ACAO_VOLTAR          = 0;
    final static int ACAO_CONSULTAR_TODOS = 1;
    final static int ACAO_VISUALIZAR      = 2;
    final static int ACAO_EXCLUIR         = 3;
    final static int ACAO_ALTERAR         = 4;
    final static int ACAO_INCLUIR         = 5;

    final static int ACAO_EXCLUIR_RELACIONAMENTO = 1;
    final static int ACAO_INCLUIR_RELACIONAMENTO = 2;

    final static String[] ACOES                = {"Voltar", "Consultar todos", "Visualizar", "Excluir", "Alterar", "Incluir"};
    final static String[] ACOES_RELACIONAMENTO = {"Voltar", "Excluir", "Incluir"};

    final static String[] NIVEL_GRADUACAO = {"Gradudo", "Mestre", "Doutor"};

    private static int getMensagemEscolhaClasse(){
        return JOptionPane.showOptionDialog(null, "Deseja dar manutenção em qual classe:", "", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, CLASSES, null);
    }

    private static int getMensagemAcao(){
        return JOptionPane.showOptionDialog(null, "Escolha uma das opções:", "", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, ACOES, null);
    }

    private static int getMensagemAcaoRelacionamento(){
        return JOptionPane.showOptionDialog(null, "Escolha uma das opções:", "", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, ACOES_RELACIONAMENTO, null);
    }

    public static void main(String[] args) throws InterruptedException {
        boolean ok    = true;
        boolean cnxOk = false;
        String ip     = "";

        do {
            ip = JOptionPane.showInputDialog("Digite o IP:");

            if(ip == null){
                return;
            }

            cnxOk = Socket.conexaoIp(ip);
        }
        while(cnxOk == false);

        do {
            int classe = getMensagemEscolhaClasse();

            if(classe == BOTAO_FECHAR){
                return;
            }

            if(classe == CLASSE_TURMA_ALUNO || 
               classe == CLASSE_TURMA_PRFESSOR){
                ok = manutencaoClasseRelacionamento(classe, ip);
            }
            else {
                ok = manutencaoClassePadrao(classe, ip);
            }

        } while (ok);
    }

    private static boolean manutencaoClassePadrao(int classe, String ip) throws InterruptedException {
        int acao = getMensagemAcao();

        switch (acao) {
            case ACAO_CONSULTAR_TODOS: 
                if(classe == CLASSE_PROFESSOR){
                    Socket.conexao(Professor.list(), ip);
                }
                else if(classe == CLASSE_ALUNO){
                    Socket.conexao(Aluno.list(), ip);
                }
                else if(classe == CLASSE_TURMA){
                    Socket.conexao(Turma.list(), ip);
                }

                break;
            case ACAO_VISUALIZAR: 
                if(classe == CLASSE_PROFESSOR){
                    Socket.conexao(Professor.get(), ip);
                }
                else if(classe == CLASSE_ALUNO){
                    Socket.conexao(Aluno.get(), ip);
                }
                else if(classe == CLASSE_TURMA){
                    Socket.conexao(Turma.get(), ip);
                }

                break;
            case ACAO_EXCLUIR: 
                if(classe == CLASSE_PROFESSOR){
                    Socket.conexao(Professor.delete(), ip);
                }
                else if(classe == CLASSE_ALUNO){
                    Socket.conexao(Aluno.delete(), ip);
                }
                else if(classe == CLASSE_TURMA){
                    Socket.conexao(Turma.delete(), ip);
                }

                break;
            case ACAO_ALTERAR: 
                if(classe == CLASSE_PROFESSOR){
                    Socket.conexao(Professor.update(), ip);
                }
                else if(classe == CLASSE_ALUNO){
                    Socket.conexao(Aluno.update(), ip);
                }
                else if(classe == CLASSE_TURMA){
                    Socket.conexao(Turma.update(), ip);
                }

                break;
            case ACAO_INCLUIR: 
                if(classe == CLASSE_PROFESSOR){
                    Socket.conexao(Professor.insert(), ip);
                }
                else if(classe == CLASSE_ALUNO){
                    Socket.conexao(Aluno.insert(), ip);
                }
                else if(classe == CLASSE_TURMA){
                    Socket.conexao(Turma.insert(), ip);
                }

                break;
        }

        return acao != -1;
    }

    private static boolean manutencaoClasseRelacionamento(int classe, String ip) throws InterruptedException {
        int acao = getMensagemAcaoRelacionamento();

        switch (acao) {
            case ACAO_INCLUIR_RELACIONAMENTO: 
                if(classe == CLASSE_TURMA_ALUNO){
                    Socket.conexao(TurmaAluno.insert(), ip);
                }
                else if(classe == CLASSE_TURMA_PRFESSOR){
                    Socket.conexao(TurmaProfessor.insert(), ip);
                }

                break;
            case ACAO_EXCLUIR_RELACIONAMENTO: 
                if(classe == CLASSE_TURMA_ALUNO){
                    Socket.conexao(TurmaAluno.delete(), ip);
                }
                else if(classe == CLASSE_TURMA_PRFESSOR){
                    Socket.conexao(TurmaProfessor.delete(), ip);
                }

                break;
        }

        return acao != -1;
    }
}