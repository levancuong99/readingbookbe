package k17.example.readingbook.service;

import k17.example.readingbook.entity.Proposal;
import k17.example.readingbook.model.dto.PropPagingDto;
import k17.example.readingbook.model.request.ParamProp;
import k17.example.readingbook.repository.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProposalServiceImpl implements ProposalService{
    int numberRowPerPage=5;
    @Autowired
    private ProposalRepository proposalRepository;

    @Override
    public Proposal getPropById(int id) {
        return proposalRepository.findByPropId(id);
    }

    @Override
    public List<Proposal> getAllProp() {
        return proposalRepository.findAllBy();
    }

    @Override
    public void deletePropById(int id) {
        proposalRepository.delete(proposalRepository.findByPropId(id));
    }

    @Override
    public Proposal updatePropById(ParamProp param, int id) {
        Proposal proposal=proposalRepository.findByPropId(id);
        proposal.setAuthorName(param.getAuthorName());
        proposal.setBookNameProp(param.getBookName());
        proposal.setRemark(param.getRemark());
        proposal.setCreatedAt(new Date());
        proposalRepository.save(proposal);
        return proposal;
    }

    @Override
    public Proposal createProp(ParamProp param) {
        Proposal proposal=new Proposal();
        proposal.setAuthorName(param.getAuthorName());
        proposal.setBookNameProp(param.getBookName());
        proposal.setRemark(param.getRemark());
        proposal.setCreatedAt(new Date());
        proposalRepository.save(proposal);
        return proposal;
    }

    @Override
    public PropPagingDto getAllPropPaging(int pageNumber) {
        List<Proposal> proposalList=proposalRepository.findAllBy();
        proposalList=proposalList.stream().sorted(new Comparator<Proposal>() {
            @Override
            public int compare(Proposal o1, Proposal o2) {
                return o2.getCreatedAt().compareTo(o1.getCreatedAt());
            }
        }).collect(Collectors.toList());
        int numberAllRow=proposalList.size();
        int totalPage=numberAllRow/numberRowPerPage+1;
        PropPagingDto propPagingDto=new PropPagingDto();
        propPagingDto.setCurrentPage(pageNumber);
        propPagingDto.setTotalPage(totalPage);
        propPagingDto.setAllRow(numberAllRow);

        int endIndex=0;
        int startIndex=(pageNumber-1)*numberRowPerPage;
        if(startIndex+numberRowPerPage<=numberAllRow) {
            endIndex=startIndex+numberRowPerPage;
        }else {
            endIndex=numberAllRow;
        }
        List<Proposal> proposals=new ArrayList<>();
        for(int i=startIndex; i<endIndex;i++) {
            proposals.add(proposalList.get(i));
        }
        propPagingDto.setProposals(proposals);
        propPagingDto.setNumberRowCurrentpage(numberRowPerPage);
        return propPagingDto;
    }
}
