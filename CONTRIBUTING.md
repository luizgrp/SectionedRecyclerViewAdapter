# How to contribute

First off, thank you for considering contributing to SectionedRecyclerViewAdapter library.

Following these guidelines helps to communicate that you respect the time of the developers managing and developing this open source project. In return, they should reciprocate that respect in addressing your issue, assessing changes, and helping you finalize your pull requests.

There are many ways to contribute:
- improving the documentation;
- submitting bug reports;
- fixing bugs reported;
- answering questions from the [issue tracker](https://github.com/luizgrp/SectionedRecyclerViewAdapter/issues) or [Stack Overflow](https://stackoverflow.com/search?q=sectionedrecyclerviewadapter);
- submitting feature requests;
- writing code which can be incorporated into the library itself;

Goal
----------------
The goal of SectionedRecyclerViewAdapter library is to provide a simple way of grouping repeatable items into sections. Each section can have a header and/or footer and can have their states changed individually.

Support questions
----------------

Please, don't use the issue tracker for support questions, use [Stack Overflow](https://stackoverflow.com/search?q=sectionedrecyclerviewadapter) instead. Search with [Google](https://www.google.com/search?q=site%3Astackoverflow.com+sectionedrecyclerviewadapter) first using: ``site:stackoverflow.com sectionedrecyclerviewadapter {search term, exception message, etc.}``

Reporting issues
----------------

- Describe what you expected to happen.
- Describe what actually happened. Include the full traceback if there was an exception.
- If possible, include a [minimal, complete, and verifiable example](https://stackoverflow.com/help/mcve) to help
  us identify the issue. This also helps check that the issue is not with your
  own code.
- Provide the library version used. If possible, check if this issue is already fixed in the repository.

Submitting pull requests
----------------

Before submitting a pull request, add your suggestion to the [issue tracker](https://github.com/luizgrp/SectionedRecyclerViewAdapter/issues) and provide with it:
#### Use Case
- Describe the motivation to add this new feature and which problems it will tackle.

> It would be nice to have a helper method in the adapter to notify that the visibility of the header of a section was changed.
> It's known that this can be done with the native notifyItemInserted and notifyItemRemoved methods but it would be nice to have the logic to calculate the header position wrapped in a method of the adapter.

#### Design
- Which methods will be created/modified/deleted in the existing classes.
- Which classes will be created/deleted.
- Explanation for each of the changes listed above.

> Create methods:
>
> - notifyHeaderInsertedInSection
>
> Get position with getHeaderPositionInAdapter and call callSuperNotifyItemInserted
>
> - notifyHeaderRemovedInSection
>
> Get position with getSectionPosition and call callSuperNotifyItemInserted

#### How to use
How the developers will use the new methods/classes introduced by these changes.
> Simply call:
>
> adapter.notifyHeaderInsertedInSection(section);
