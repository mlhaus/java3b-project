$(".btn-open").on("click", function() {
    let title = $(this).attr("data-title");
    let population = $(this).attr("data-population");
    let region = $(this).attr("data-region");
    // console.log(`${title} ${population} ${region}`);
    $(".modal-title").html(title);
    $(".modal-population").html(population);
    $(".modal-region").html(region);
});