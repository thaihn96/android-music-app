package com.framgia.thaihn.tmusic.screen.genre;

import com.framgia.thaihn.tmusic.data.model.Song;
import com.framgia.thaihn.tmusic.data.repository.SongRepository;
import com.framgia.thaihn.tmusic.data.source.SongDataSource;

import java.util.List;

public class GenrePresenter implements GenreContract.Presenter,
        SongDataSource.OnFetchDataListener<Song> {

    private GenreContract.View mView;
    private SongRepository mSongRepository;


    public GenrePresenter() {
        mSongRepository = SongRepository.getInstance();
    }

    @Override
    public void loadMusic(int position, int limit, int offset) {
        mSongRepository.getSongsRemote(position, limit, offset, this);
        mView.showProgress();
    }

    @Override
    public void loadAllMusic(int limit, int offset) {
        mSongRepository.getAllSongsRemote(limit, offset, this);
    }

    @Override
    public void setView(GenreContract.View view) {
        this.mView = view;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void onFetchDataSuccess(int position, List<Song> list) {
        mView.hideProgress();
        mView.showData(position, list);
    }

    @Override
    public void onFetchDataError(String message) {
        mView.hideProgress();
        mView.showError(message);
    }
}
