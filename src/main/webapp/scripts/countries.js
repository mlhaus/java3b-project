$(".btn-open").on("click", function() {
    let title = $(this).attr("data-title");
    let population = $(this).attr("data-population");
    let region = $(this).attr("data-region");
    let latitude = $(this).attr("data-latitude");
    let longitude = $(this).attr("data-longitude");
    // console.log(`${title} ${population} ${region}`);
    $(".modal-title").html(title);
    $(".modal-population").html(population);
    $(".modal-region").html(region);
    $(".modal-map").html(`
            <iframe 
            width="100%" 
            height="480" 
            frameborder="0" 
            scrolling="no" 
            marginheight="0" 
            marginwidth="0"
            src="https://maps.google.com/maps?q=${latitude},${longitude}&amp;z=4&amp;output=embed">
            <br>
            <a href="https://www.google.com/maps/@${latitude},${longitude},4z" target="_blank">
            See full page map
            </a></iframe>
    `);
});
