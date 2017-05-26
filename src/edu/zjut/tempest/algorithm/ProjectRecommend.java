package edu.zjut.tempest.algorithm;

import java.util.ArrayList;
import java.util.List;

import edu.zjut.tempest.entity.Project;

public class ProjectRecommend {

	public static class Simi {
		double value;   //相似值
		int jpId;   //参与过的项目id
		int npId;   //发布中的项目id
	}
	
	public List<ProjectData> projectCF(List<Project> jpList, List<Project> npList) {
		int jpNum = jpList.size();
		int npNum = npList.size();
		
		Simi[][] simi = new Simi[jpNum][npNum];
		for(int i=0; i<jpNum; i++)
			for(int j=0; j<npNum; j++)
				simi[i][j] = new Simi();
		
		for(int i=0; i<jpNum; i++) {
			for(int j=0; j<npNum; j++) {
				SimilarCos sc1 = new SimilarCos(jpList.get(i).getName(), npList.get(j).getName());
				SimilarCos sc2 = new SimilarCos(jpList.get(i).getDescription(), npList.get(j).getDescription());
				SimilarCos sc3 = new SimilarCos(jpList.get(i).getRequirement(), npList.get(j).getRequirement());
				
				simi[i][j].value = 0.2*sc1.sim() + 0.4*sc2.sim() + 0.4*sc3.sim();
				simi[i][j].jpId = jpList.get(i).getId();
				simi[i][j].npId = npList.get(j).getId();
			}
		}
		
		
		List<ProjectData> cfList = new ArrayList<ProjectData>();
		//找出推荐的项目与所有参加过的项目的相似度最大值
		for(int i=0; i<npNum; i++) {
			ProjectData pd = new ProjectData();
			Simi max = simi[0][i];
			for(int j=0; j<jpNum; j++) {
				if(simi[j][i].value > max.value) {
					max = simi[j][i];
				}
			}
			pd.projectId = max.npId;
			pd.score = max.value;
			
			cfList.add(pd);
		}
		return cfList;
	}
	
}
