package mft.service;

import mft.model.entity.Loan;
import mft.model.repository.LoanRepository;

import java.util.List;

public class LoanService {
    public static void save(Loan loan) throws Exception {
        try (LoanRepository loanRepository = new LoanRepository()) {
            loanRepository.save(loan);
        }
    }

    public static void edit(Loan loan) throws Exception {
        try (LoanRepository loanRepository = new LoanRepository()) {
            loanRepository.edit(loan);
        }
    }

    public static void delete(int id) throws Exception {
        try (LoanRepository loanRepository = new LoanRepository()) {
            loanRepository.delete(id);
        }
    }

    public static List<Loan> findAll() throws Exception {
        try (LoanRepository loanRepository = new LoanRepository()) {
            return loanRepository.findAll();
        }
    }

    public static Loan findById(int id) throws Exception {
        try (LoanRepository loanRepository = new LoanRepository()) {
            return loanRepository.findById(id);
        }
    }

    public static List<Loan> findByPersonNameAndFamily(String name, String family) throws Exception {
        try (LoanRepository loanRepository = new LoanRepository()) {
            return loanRepository.findByPersonNameAndFamily(name, family);
        }
    }

    public List<Loan> findByBookTitle(String title) throws Exception{
        try (LoanRepository loanRepository = new LoanRepository()) {
            return loanRepository.findByBookTitle(title);
        }
    }

    public List<Loan> findNotReturnedLoans() throws Exception{
        try (LoanRepository loanRepository = new LoanRepository()) {
            return loanRepository.findNotReturnedLoans();
        }
    }
}
