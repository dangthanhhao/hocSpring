package com.dangthanhhao.DAO;

import org.hibernate.Query;
import org.hibernate.Session;

import com.dangthanhhao.entity.Product;
import com.dangthanhhao.entity.SongExample;

public class SongDAO {
	Session session;

	public SongDAO(Session session) {
		super();
		this.session = session;
	}
	public SongExample getSongByName(String songName) {
		String hql="From SongExample where exampleSongName="+songName;
		Query query=session.createQuery(hql);
		System.out.println("b");
		return (SongExample) query.uniqueResult();
	}
}
