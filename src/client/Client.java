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

    final static String[] ACOES = {"Voltar", "Consultar todos", "Visualizar", "Excluir", "Alterar", "Incluir"};

    final static String[] NIVEL_GRADUACAO = {"Gradudo", "Mestre", "Doutor"};

    private static int getMensagemEscolhaClasse(){
        return JOptionPane.showOptionDialog(null, "Deseja dar manutenção em qual classe:", "", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, CLASSES, null);
    }

    private static int getMensagemAcao(){
        return JOptionPane.showOptionDialog(null, "Escolha uma das opções:", "", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, ACOES, null);
    }

    public static void main(String[] args) throws InterruptedException {
        boolean ok = true;

        do {

            int classe = getMensagemEscolhaClasse();

            if(classe == BOTAO_FECHAR){
                break;
            }

            if(classe == CLASSE_TURMA_ALUNO){
                Socket.conexao(TurmaAluno.insert());
            }
            else if (classe == CLASSE_TURMA_PRFESSOR){
                Socket.conexao(TurmaProfessor.insert());
            }
            else {
                ok = manutencaoClasse(classe);
            }

        } while (ok);
    }

    private static boolean manutencaoClasse(int classe) throws InterruptedException {
        int acao = getMensagemAcao();

        switch (acao) {
            case ACAO_CONSULTAR_TODOS: 
                if(classe == CLASSE_PROFESSOR){
                    Socket.conexao(Professor.list());
                }
                else if(classe == CLASSE_ALUNO){
                    Socket.conexao(Aluno.list());
                }
                else if(classe == CLASSE_TURMA){
                    Socket.conexao(Turma.list());
                }

                break;
            case ACAO_VISUALIZAR: 
                if(classe == CLASSE_PROFESSOR){
                    Socket.conexao(Professor.get());
                }
                else if(classe == CLASSE_ALUNO){
                    Socket.conexao(Aluno.get());
                }
                else if(classe == CLASSE_TURMA){
                    Socket.conexao(Turma.get());
                }

                break;
            case ACAO_EXCLUIR: 
                if(classe == CLASSE_PROFESSOR){
                    Socket.conexao(Professor.delete());
                }
                else if(classe == CLASSE_ALUNO){
                    Socket.conexao(Aluno.delete());
                }
                else if(classe == CLASSE_TURMA){
                    Socket.conexao(Turma.delete());
                }

                break;
            case ACAO_ALTERAR: 
                if(classe == CLASSE_PROFESSOR){
                    Socket.conexao(Professor.update());
                }
                else if(classe == CLASSE_ALUNO){
                    Socket.conexao(Aluno.update());
                }
                else if(classe == CLASSE_TURMA){
                    Socket.conexao(Turma.update());
                }

                break;
            case ACAO_INCLUIR: 
                if(classe == CLASSE_PROFESSOR){
                    Socket.conexao(Professor.insert());
                }
                else if(classe == CLASSE_ALUNO){
                    Socket.conexao(Aluno.insert());
                }
                else if(classe == CLASSE_TURMA){
                    Socket.conexao(Turma.insert());
                }

                break;
        }

        return acao != -1;
    }
    
}