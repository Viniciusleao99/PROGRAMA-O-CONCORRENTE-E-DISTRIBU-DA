// Classe Main
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final int NUMERO_TOTAL_CLIENTES = 5;
    public static final int MAX_COMPRAS = 1000;
    public static List<Loja> lojas = new ArrayList<>();
    public static int clientesComSaldoZero = 0;

    public static void main(String[] args) {
        // Inicialização do sistema bancário
        Banco banco = new Banco();
        banco.criarContaComSaldoInicial(0.0);

        // Inicialização das lojas
        Loja loja1 = new Loja("Loja 1", banco);
        Loja loja2 = new Loja("Loja 2", banco);
        lojas.add(loja1);
        lojas.add(loja2);

        // Inicialização dos funcionários
        new Funcionario("Funcionário 1 Loja 1", loja1.getConta(), banco.criarContaComSaldoInicial(0.0));
        new Funcionario("Funcionário 2 Loja 1", loja1.getConta(), banco.criarContaComSaldoInicial(0.0));
        new Funcionario("Funcionário 1 Loja 2", loja2.getConta(), banco.criarContaComSaldoInicial(0.0));
        new Funcionario("Funcionário 2 Loja 2", loja2.getConta(), banco.criarContaComSaldoInicial(0.0));

        // Inicialização dos clientes
        for (int i = 1; i <= NUMERO_TOTAL_CLIENTES; i++) {
            Cliente cliente = new Cliente("Cliente " + i, banco.criarContaComSaldoInicial(1000.0), MAX_COMPRAS, lojas, NUMERO_TOTAL_CLIENTES, clientesComSaldoZero);
            cliente.start();
        }
    }
}
