import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CadastroTest {

    private Cadastro cadastro;

    @BeforeAll
    static void antesDeTodosOsTestes(){
        System.out.println("Iniciando os testes...");
    }

    @BeforeEach
    void prepararTeste() {
        cadastro = new Cadastro();
    }

    @Test
    @Order(1)
    @DisplayName("Deveria cadastrar um novo elemento")
    void testeCadastroElemento() {
        cadastro.cadastrarElemento("Cadeira");
        List<String> elementos = cadastro.getElementos();
        assertNotNull(elementos);
        assertEquals(1, elementos.size());
        assertEquals("Cadeira", elementos.get(0));
    }

    @Test
    @Order(2)
    @DisplayName("Deveria editar um elemento já cadastrado")
    void testeEditarElemento() {
        cadastro.cadastrarElemento("Cadeira");
        cadastro.editarElemento(0,"Cadeira de Balanço");
        List<String> elementos = cadastro.getElementos();
        assertEquals(1, elementos.size());
        assertEquals("Cadeira de Balanço", elementos.get(0));
    }

    @Test
    @Order(3)
    @DisplayName("Deveria lançar uma exceção ao tentar editar um elemento que não existe")
    void testeEditarElementoInvalido() {
       assertThrows(IndexOutOfBoundsException.class,
               () -> cadastro.editarElemento(0, "Cadeira de Balanço"));
    }

    @Test
    @Order(4)
    @DisplayName("Deveria excluir um novo elemento já cadastrado")
    void testeExcluirElemento() {
        cadastro.cadastrarElemento("Cadeira");
        cadastro.excluirElemento(0);
        List<String> elementos = cadastro.getElementos();
        assertTrue(elementos.isEmpty());
    }

    @Test
    @Order(5)
    @DisplayName("Deveria lançar uma exceção ao tentar excluir um elemento que não existe")
    void testeExcluirElementoInvalido() {
        assertThrows(IndexOutOfBoundsException.class,
            () -> cadastro.excluirElemento(0));
    }

    @AfterEach
    void limparDepoisDosTestes() {
        cadastro = null;
    }

    @AfterAll
    static void depoisDeTodosOsTestes() {
        System.out.println("Todos os testes finalizados!");
    }

    @Test
    @Order(6)
    @EnabledOnOs({OS.LINUX, OS.MAC})
    @DisplayName("Esse teste só executa em Linux ou Mac")
    void testeExcluirOutroElementoInvalido() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> cadastro.excluirElemento(0));
    }


}