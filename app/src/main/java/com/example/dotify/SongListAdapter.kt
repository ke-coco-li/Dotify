package com.example.dotify

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ericchee.songdataprovider.Song
import com.example.dotify.databinding.SongListBinding

class SongListAdapter(private var songList: List<Song>): RecyclerView.Adapter<SongListAdapter.SongViewHolder>()  {

    var onSongClickListener: (position: Int, name: Song) -> Unit = { _, _ ->  }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val binding = SongListBinding.inflate(LayoutInflater.from(parent.context))
        return SongViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = songList[position]

        with(holder.binding) {
            albumCover.setImageResource(song.smallImageID)
            songTitle.text = song.title
            artistName.text = song.artist
            itemRoot.setOnClickListener {
                onSongClickListener(position, song)
            }
        }
    }

    override fun getItemCount(): Int = songList.size

    fun updateSongs(newSongList: List<Song>) {
        this.songList = newSongList
        notifyDataSetChanged()
    }

    class SongViewHolder(val binding: SongListBinding): RecyclerView.ViewHolder(binding.root)

}