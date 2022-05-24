package ru.ithub.examination.core.router;


public interface Router {
    String API = "/api";
    String API_V1 = API + "/v1";

    interface Authentication {
        String NAME = "/auth";
        String ROOT = Router.API_V1 + NAME;

        interface Sign {
            String NAME = "/sign";
            String ROOT = Authentication.ROOT + NAME;
            interface In {
                String NAME = "/in";
                String ROOT = Sign.ROOT + NAME;
            }

            interface Up {
                String NAME = "/up";
                String ROOT = Sign.ROOT + NAME;
            }
        }
    }

    interface User {
        String NAME = "/users";
        String ROOT = Router.API_V1 + NAME;

        interface Id {
            String NAME = "/{id}";
            String ROOT = User.ROOT + NAME;
        }
    }

    interface Passing {
        String NAME = "/passing";
        String ROOT = Router.API_V1 + NAME;

        interface Search {
            String NAME = "/search";
            String ROOT = Passing.ROOT + NAME;

            interface Exam {
                String NAME = "/exams";
                String ROOT = Search.ROOT + NAME;

                interface Id {
                    String NAME = "/{id}";
                    String ROOT = Exam.ROOT + NAME;
                }
            }

            interface Passes {
                String NAME = "/passes";
                String ROOT = Search.ROOT + NAME;
            }
        }

        interface Start {
            String NAME = "/start/{examId}";
            String ROOT = Passing.ROOT + NAME;
        }

        interface End {
            String NAME = "/end/{examPassingId}";
            String ROOT = Passing.ROOT + NAME;
        }
    }

    interface Exam {
        String NAME = "/exams";
        String ROOT = Router.API_V1 + NAME;

        interface Id {
            String NAME = "/{id}";
            String ROOT = Exam.ROOT + NAME;
        }

        interface Question {
            String NAME = "/questions";
            String ROOT = Exam.ROOT + NAME;

            interface Id {
                String NAME = "/{id}";
                String ROOT = Question.ROOT + NAME;
            }

            interface Answer {
                String NAME = "/answers";
                String ROOT = Question.ROOT + NAME;

                interface Id {
                    String NAME = "/{id}";
                    String ROOT = Answer.ROOT + NAME;
                }
            }
        }
    }
}