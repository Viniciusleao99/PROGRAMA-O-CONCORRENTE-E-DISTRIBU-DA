public class Funcionario {
    private String nome;
    private ContaBancaria contaSalario;
    private ContaBancaria contaInvestimento;

    public Funcionario(String nome, ContaBancaria contaSalario, ContaBancaria contaInvestimento) {
        this.nome = nome;
        this.contaSalario = contaSalario;
        this.contaInvestimento = contaInvestimento;
    }

    public String getNome() {
        return nome;
    }

    public ContaBancaria getContaSalario() {
        return contaSalario;
    }

    public ContaBancaria getContaInvestimento() {
        return contaInvestimento;
    }
}
