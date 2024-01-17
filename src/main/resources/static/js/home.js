function formataData() {
    const dataNascimentoStr = $("#dataNascimento").val();
    let dataNascimento = new Date(dataNascimentoStr);

    dataNascimento.setDate(dataNascimento.getDate() + 1);

    let dia = ("0" + dataNascimento.getDate()).slice(-2);
    let mes = ("0" + (dataNascimento.getMonth() + 1)).slice(-2);
    let ano = dataNascimento.getFullYear();

    return dia + "/" + mes + "/" + ano;
}

function ehCampoVazioOuNulo(valor) {
    return valor == null || (valor != null && valor.trim() === '');
}

function alertEFocusNoCampo(campo, mensagem) {
    $(campo).focus();
    alert(mensagem);
}

function obterDadosDoFormulario() {
    let cpf = $("#cpf").val();
    let nome = $("#nome").val();
    let idade = $("#idade").val();
    let email = $('#email').val();
    let dataNascimento = formataData();
    let genero = $("#genero").val();
    let telefone = $("#telefone").val();
    let endereco = $("#endereco").val();
    let profissao = $("#profissao").val();
    let nivelEscolaridade = $("#nivelEscolaridade").val();
    let statusRelacionamento = $('#statusRelacionamento').val();

    // Verificações para campos nulos ou vazios
    if (ehCampoVazioOuNulo(cpf)) {
        alertEFocusNoCampo("#cpf", 'Informe o cpf');
        return null;
    }

    if (ehCampoVazioOuNulo(nome)) {
        alertEFocusNoCampo("#nome", 'Informe uma idade');
        return null;
    }

    if (ehCampoVazioOuNulo(idade)) {
        alertEFocusNoCampo("#idade", 'Informe uma idade');
        return null;
    }

    if (ehCampoVazioOuNulo(email)) {
        alertEFocusNoCampo("#email", 'Informe um email');
        return null;
    }

    if (ehCampoVazioOuNulo(dataNascimento)) {
        alertEFocusNoCampo("#dataNascimento", 'Informe uma data de nascimento');
        return null;
    }

    if (ehCampoVazioOuNulo(genero)) {
        alertEFocusNoCampo("#genero", 'Informe um genero');
        return null;
    }

    if (ehCampoVazioOuNulo(telefone)) {
        alertEFocusNoCampo("#telefone", 'Informe um telefone');
        return null;
    }

    if (ehCampoVazioOuNulo(endereco)) {
        alertEFocusNoCampo("#endereco", 'Informe um endereco');
        return null;
    }

    if (ehCampoVazioOuNulo(profissao)) {
        alertEFocusNoCampo("#profissao", 'Informe uma profissao');
        return null;
    }

    if (ehCampoVazioOuNulo(nivelEscolaridade)) {
        alertEFocusNoCampo("#nivelEscolaridade", 'Informe o nivel de escolaridade');
        return null;
    }

    if (ehCampoVazioOuNulo(statusRelacionamento)) {
        alertEFocusNoCampo("#statusRelacionamento", 'Informe o status de relacionamento');
        return null;
    }

    return {
        cpf:cpf,
        nome: nome,
        idade: idade,
        email: email,
        dataNascimento: dataNascimento,
        genero: genero,
        telefone: telefone,
        endereco: endereco,
        profissao: profissao,
        nivelEscolaridade: nivelEscolaridade,
        statusRelacionamento: statusRelacionamento
    };
}


function deletarusuarioForms() {
    let id = $('#id').val();
    if (id != null && id.trim() != '') {
        deletarUsuario(id);
        document.getElementById('formcadastroUser').reset();
    }
}


function pesquisarUsuario() {
    var nome = $('#nameBusca').val();
    if (nome != null && nome.trim() != '') {

        $.ajax({
            method: "GET",
            url: "/aprendendo-spring/user/buscarnome/" + nome,
            data: { name: nome },
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                $('#tabelaResultados > tbody > tr').remove();

                for (var i = 0; i < response.length; i++) {
                    var usuario = response[i];

                    var row = '<tr id="' + usuario.id + '">';
                    row += '<td>' + usuario.id + '</td>';
                    row += '<td>' + usuario.cpf + '</td>';
                    row += '<td>' + usuario.nome + '</td>';
                    row += '<td>' + usuario.idade + '</td>';
                    row += '<td>' + usuario.email + '</td>';
                    row += '<td>' + usuario.dataNascimento + '</td>';
                    row += '<td>' + usuario.genero + '</td>';
                    row += '<td>' + usuario.telefone + '</td>';
                    row += '<td>' + usuario.endereco + '</td>';
                    row += '<td>' + usuario.profissao + '</td>';
                    row += '<td>' + usuario.nivelEscolaridade + '</td>';
                    row += '<td>' + usuario.statusRelacionamento + '</td>';
                    row += '<td><button type="button" onclick="colocarEmEdicao(' + usuario.id + ')" class="btn btn-info">Visualizar</button></td>';
                    row += '<td><button type="button" onclick="deletarUsuario(' + usuario.id + ')" class="btn btn-dark">Deletar</button></td>';
                    row += '</tr>';

                    $('#tabelaResultados > tbody').append(row);
                }
            }

        }).fail(function (xhr, status, errorThrown) {
            alert("Erro ao buscar usuario: " + xhr.responseText + " " + status + " " + errorThrown)
        });
    }
}

function formatarData(data) {
    let partes = data.split('/');
    return partes[2] + '-' + partes[1] + '-' + partes[0];
}

function colocarEmEdicao(id) {

    $.ajax({
        method: "GET",
        url: "/aprendendo-spring/user/" + id,
        contentType: "application/json; charset=utf-8",
        success: function (response) {
            // Atualize os campos do formulário com os dados recebidos
            $("#id").val(response.id);
            $("#cpf").val(response.cpf);
            $("#nome").val(response.nome);
            $("#idade").val(response.idade);
            $("#email").val(response.email);

            let dataNascimento = formatarData(response.dataNascimento);
            $("#dataNascimento").val(dataNascimento);

            $("#genero").val(response.genero);
            $("#telefone").val(response.telefone);
            $("#endereco").val(response.endereco);
            $("#profissao").val(response.profissao);
            $("#nivelEscolaridade").val(response.nivelEscolaridade);
            $("#statusRelacionamento").val(response.statusRelacionamento);

            $("#modalPesquisarUsuario").modal('hide');
        }

    }).fail(function (xhr, status, errorThrown) {
        alert("Erro ao buscar usuário por id: " + xhr.responseText + " " + status + " " + errorThrown)
    });
}


function deletarUsuario(id) {

    if (confirm("Deseja realmente deletar?")) {

        $.ajax({
            method: "DELETE",
            url: "/aprendendo-spring/user/deletar/" + id,
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                $('#' + id).remove();
                alert(response);
            }

        }).fail(function (xhr, status, errorThrown) {
            alert("Erro ao deletar usuario por id: " + xhr.responseText + " " + status + " " + errorThrown)
        });
    }
}


function salvarUsuario() {
    let dadosUsuario = obterDadosDoFormulario();
    if (!dadosUsuario) {
        alert("Preencha todos os campos antes de salvar.");
        return;
    }

    $.ajax({
        method: "POST",
        url: "/aprendendo-spring/user/salvar",
        data: JSON.stringify(dadosUsuario),
        contentType: "application/json; charset=utf-8",
        success: function (response) {
            $("#id").val(response.id);
            alert("Salvo com sucesso no banco de dados")
            document.getElementById('formcadastroUser').reset();
        }
    }).fail(function (xhr, status, errorThrown) {
        alert("Erro ao salvar usuario: " + xhr.responseText + " " + status + " " + errorThrown)
    });
}