
function deletarusuarioForms() {
    var id = $('#id').val();
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
                    $('#tabelaResultados > tbody').append('<tr id="' + response[i].id + '"><td>' + response[i].id + '</td> <td>' + response[i].nome + '</td> <td><button type="button" onclick="colocarEmEdicao(' + response[i].id + ')" class="btn btn-info">Visualizar</button></td><td><button type="button" onclick="deletarUsuario(' + response[i].id + ')" class="btn btn-dark">Deletar</button></td></tr>');
                }
            }

        }).fail(function (xhr, status, errorThrown) {
            alert("Erro ao buscar usuario: " + xhr.responseText + " " + status + " " + errorThrown)
        });
    }
}

function colocarEmEdicao(id) {

    $.ajax({
        method: "GET",
        url: "/aprendendo-spring/user/" + id,
        contentType: "application/json; charset=utf-8",
        success: function (response) {
            $("#id").val(response.id);
            $("#nome").val(response.nome);
            $("#idade").val(response.idade);

            $("#modalPesquisarUsuario").modal('hide');
        }

    }).fail(function (xhr, status, errorThrown) {
        alert("Erro ao buscar usuario por id: " + xhr.responseText + " " + status + " " + errorThrown)
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
    var id = $("#id").val();
    var nome = $("#nome").val();
    var idade = $("#idade").val();

    if (nome == null || nome != null && nome.trim() == '') {
        $("#nome").focus();
        alert('Informe um nome');
        return;
    }
    if (idade == null || idade != null && idade.trim() == '') {
        $("#idade").focus();
        alert('Informe uma idade');
        return;
    }

    var dadosUsuario = {
        id: id,
        nome: nome,
        idade: idade
    };

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