// Classe Loja
public class Loja {
    private String nome;
    private ContaBancaria conta;
    private Banco banco;

    public Loja(String nome, Banco banco) {
        this.nome = nome;
        this.banco = banco;
        this.conta = banco.criarContaComSaldoInicial(0.0);
    }

    public String getNome() {
        return nome;
    }

    public ContaBancaria getConta() {
        return conta;
    }

    public synchronized void receberPagamento(double valor) {
        conta.creditar(valor);
        System.out.println("A loja " + nome + " recebeu um pagamento de R$ " + valor);
    }

    // Método para pagar o salário do funcionário
    public void pagarSalario(Funcionario funcionario, double valor) {
        banco.transferir(conta, funcionario.getContaSalario(), valor);
        System.out.println("A loja " + nome + " pagou o salário de R$ " + valor + " ao funcionário " + funcionario.getNome());
    }
}
