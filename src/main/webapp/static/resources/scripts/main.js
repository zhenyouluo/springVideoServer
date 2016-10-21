'use strict';

$(function () {

    var $videoSrc = $('.video-src'),
        $videoSwitchControls = $('.video-switch-controls'),
        $contentBox = $('.content-box'),
        $btnPlayFirst = $contentBox.find('.video-col').first().find('.btn-play'),
        $btnPlayLast = $contentBox.find('.video-col').last().find('.btn-play'),
        firstVidSrc = $btnPlayFirst.data('video-src'),
        lastVidSrc = $btnPlayLast.data('video-src');

    $('.btn-play').on('click', function (e) {

        e.preventDefault();
        var videoSrcPath = $(this).data('video-src'),
            videoWidth = $(this).data('video-width'),
            videoHeight = $(this).data('video-height');
        $(this).addClass('btn-play-active');

        $videoSrc.attr('src', videoSrcPath);
        $('.video-src-container').removeClass('video-src-hidden');
        $videoSwitchControls.css('visibility', 'visible');

        $videoSrc.css({
            'position': 'fixed',
            'top': $(window).height() / 2 + 'px',
            'margin-top': '-' + videoHeight / 2 + 'px',
            'right': '50%',
            'margin-right': '-' + videoWidth / 2 + 'px'
        });
    });

    $('.video-src-container').on('click', function (e) {

        $(this).addClass('video-src-hidden');
        $videoSwitchControls.css('visibility', 'hidden');
        document.querySelector('.video-src').pause();
        $('.btn-play-active').removeClass('.btn-play-active');
    });

    $('.video-switch-control').on('click', function (e) {

        e.preventDefault();
        var $btnPlayActive = $('.btn-play-active'),
            $btnPlayNext = $btnPlayActive.parent().closest('.video-col').next().find('.btn-play'),
            $btnPlayPrev = $btnPlayActive.parent().closest('.video-col').prev().find('.btn-play'),
            nextVidSrc = $btnPlayNext.data('video-src'),
            prevVidSrc = $btnPlayPrev.data('video-src');

        if ($(this).hasClass('video-src-next')) {
            if ($btnPlayActive.parent().closest('.video-col').next().hasClass('video-col')) {
                $videoSrc.attr('src', nextVidSrc);
                $btnPlayActive.removeClass('btn-play-active');
                $btnPlayNext.addClass('btn-play-active');
            } else {
                $videoSrc.attr('src', firstVidSrc);
                $btnPlayActive.removeClass('btn-play-active');
                $btnPlayFirst.addClass('btn-play-active');
            }
        } else {
            if ($btnPlayActive.parent().closest('.video-col').prev().hasClass('video-col')) {
                $videoSrc.attr('src', prevVidSrc);
                $btnPlayActive.removeClass('btn-play-ative');
                $btnPlayPrev.addClass('btn-play-active');
            } else {
                console.log('last');
                $videoSrc.attr('src', lastVidSrc);
                $btnPlayActive.removeClass('btn-play-active');
                $btnPlayLast.addClass('btn-play-active');
            }
        }
    });
});
//# sourceMappingURL=main.js.map