document.addEventListener("DOMContentLoaded", function (event) {
    $('#login-form').modal();
    $.ajax({
        type: "GET",
        cache: false,
        url: "js/dados.json",
        dataType: "json",
        success: function (retorno) {
            console.log(retorno);
            var valorMax = 0;
            var gastos = 0;
            for (i = 0; i < retorno.length; i++) {
                $("tbody#lista-gasto").append("" +
                    "<tr>" +
                    "<td>" + retorno[i].categoria + "</td>" +
                    "<td>" + retorno[i].valor + "</td>" +
                    "<td>" + retorno[i].data + "</td>" +
                    "<td>" + retorno[i].tipo + "</td>" +
                    "</tr>"
                );

                if (retorno[i].tipo === "Crédito") {
                    valorMax += parseFloat(retorno[i].valor);
                } else if (retorno[i].tipo === "Débito") {
                    gastos += parseFloat(retorno[i].valor);
                }
            }

            var g5 = new JustGage({
                id: "g5",
                value: gastos,
                min: 0,
                max: valorMax,
                title: "Situação Geral",
                label: "R$",
                formatNumber: true,
                decimals: 2,
                startAnimationTime: 3000,
                startAnimationType: ">",
                refreshAnimationType: "bounce",
                relativeGaugeSize: true
            });

            //CONFIGURANDO A TABELA
            var table = document.getElementById('table');
            var options = {
                sortable: true,
                searchable: true,
                fixedHeight: true,
                perPage: 5
            };

            var dataTable = new DataTable(table, options);

            /* Event listeners */
            dataTable.on('datatable.init', function (table) {
                console.log('Carregando...');
            });

            dataTable.on('datatable.change', function (table) {
                console.log("Página " + this.currentPage);
                if (this.onFirstPage) {
                    console.log("Você está na primeira página!");
                }
                if (this.onLastPage) {
                    console.log("Você está na última página!");
                }
            });

            dataTable.on('datatable.sort', function (table) {
                console.log("Ordenado: " + this.sortOrder);
            });

            if ($.modal.isActive()) {
                setTimeout(function () {
                    $.modal.close();
                }, 1000);

            }
        }

    });

});



