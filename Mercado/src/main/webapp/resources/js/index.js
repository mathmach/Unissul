
$(document).ready(function () {

    $("#newRack").click(function (e) {
        e.preventDefault();
        var btn = $("#rack");
        $.get({
            url: "rack/new",
            success: function (data) {
                btn.hide();
                $("#container").html(data);
            }
        });
        /*$.get("rack/new", function (data) {
         $(this).parent().parent().hide();
         $("#container").html(data);
         });*/
    });

    $("[id^=newProduct]").click(function (e) {
        e.preventDefault();
        var rackId = $(this).attr("id").replace(/newProduct/, "");
        $.get({
            url: "product/new",
            success: function (data) {
                $("#collapse" + rackId + " > ul").hide();
                $("#collapse" + rackId + " > ul").after(data);
                $("#rack option[value=" + rackId + "]").attr("selected", true);
                $("[data-target^='#formProduct']:last").attr("data-product-rack", rackId);
            }
        });
    });

    $("[id^=editRack]").click(function (e) {
        e.preventDefault();
        var rackId = $(this).attr("id").replace(/editRack/, "");
        $.get({
            url: "rack/" + rackId + "/edit",
            success: function (data) {
                $("#rack" + rackId).hide();
                $("#rack" + rackId).after(data);
            }
        });
    });

    $("[id^=editProduct]").click(function (e) {
        e.preventDefault();
        var productId = $(this).attr("id").replace(/editProduct/, "");
        var rackId = $(this).attr("data-product").replace(/collapse/, "");
        $.get({
            url: "product/" + productId + "/edit",
            success: function (data) {
                $("#collapse" + rackId + " > ul").attr("data-form", "#formProduct" + productId);
                $("#collapse" + rackId + " > ul").hide();
                $("#collapse" + rackId + " > ul").after(data);
            }
        });
    });

    $(document).on("click", "[data-target^='#formRack']", function (e) {
        e.preventDefault();
        var rackId = $(this).attr('data-target').replace(/#formRack/, '');
        $("#rack" + rackId).show();
    });

    $(document).on("click", "[data-target^='#formProduct']", function (e) {
        e.preventDefault();
        var productForm = $(this).attr("data-target");
        $("[data-form='" + productForm + "']").show();
        $("[data-form='" + productForm + "']").attr("data-form", "");
    });

    $("[data-rack^=collapse]").each(function () {
        var rackId = $(this).attr('data-rack').replace(/collapse/, '');
        $("#" + $(this)[0].id + " > ul > li").each(function () {
            if ($(this).hasClass("list-danger")) {
                $("#rack" + rackId + " > .panel-group").addClass("panel-danger").removeClass("panel-warning");
                $("[href='#collapse" + rackId + "'] > span").attr("data-content", "Existem itens com pouco estoque!");
                return false;
            } else if ($(this).hasClass("list-warning")) {
                $("[href='#collapse" + rackId + "'] > span").attr("data-content", "Existem itens com menos da metade do estoque!");
                $("#rack" + rackId + " > .panel-group").addClass("panel-warning");
            }
        });
    });

    $("#confirm-delete").on("show.bs.modal", function (e) {
        $(this).find("#itemId").val($(e.relatedTarget).data("href"));
        if ($(e.relatedTarget).data("type") === "rack") {
            $("#itemName").html('Você tem certeza que deseja apagar a prateleira <strong>"' + $(e.relatedTarget).data("name") + '"</strong>?');
            $("#deleteId").attr("name", "deleteRack");
        } else if ($(e.relatedTarget).data("type") === "product") {
            $("#itemName").html('Você tem certeza que deseja apagar o produto <strong>"' + $(e.relatedTarget).data("name") + '"</strong>?');
            $("#deleteId").attr("name", "deleteProduct");
        }
    });

    $('[data-toggle="popover"]').popover();

});